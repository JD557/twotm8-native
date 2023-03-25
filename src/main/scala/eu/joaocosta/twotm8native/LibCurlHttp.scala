package eu.joaocosta.twotm8native

import scala.scalanative.unsafe.*
import curl.all.*
import CURLoption.*
import scala.scalanative.unsigned.*
import scala.scalanative.runtime.libc

object LibCurlHttp extends Http {
  def get(url: String): String = Zone { implicit z =>
    val curl = curl_easy_init()
    try {
      assert(curl != null, "failured to initialise curl")
      curl_easy_setopt(curl, CURLOPT_URL, toCString(url))

      curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_data_callback)

      Captured.unsafe(new java.lang.StringBuilder) { ptr =>
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, ptr)
        val res = curl_easy_perform(curl)
        assert(
          res == CURLcode.CURLE_OK,
          "CURL Failure: " + fromCString(curl_easy_strerror(res))
        )

        (!ptr).toString()
      }
    } finally {
      curl_easy_cleanup(curl)
    }
  }

  private val write_data_callback = CFuncPtr4.fromScalaFunction {
    (
        ptr: Ptr[Byte],
        size: CSize,
        nmemb: CSize,
        userdata: Ptr[java.lang.StringBuilder]
    ) =>
      val total     = (size * nmemb).toLong
      var offset    = 0L
      val chunkSize = 16384L

      def stop = offset >= total

      def fromCStringAndSize(cstr: CString, size: Int): String = {
        if (size > 0) {
          val bytes = Array.ofDim[Byte](size)

          libc.memcpy(bytes.at(0), cstr, size.toUInt)

          new String(bytes)
        } else ""
      }

      while (!stop) {
        val size = chunkSize.min(total - offset).toInt
        (!userdata).append(fromCStringAndSize(ptr, size))
        offset += size
      }

      nmemb * size
  }

  private object GCRoots:
    private val references          = new java.util.IdentityHashMap[Object, Unit]
    def addRoot(o: Object): Unit    = references.put(o, ())
    def removeRoot(o: Object): Unit = references.remove(o)

  private object Captured:
    inline def unsafe[D: Tag, A](value: D)(inline use: Ptr[D] => A): A =
      import scalanative.runtime.*

      val rawptr = libc.malloc(sizeof[D])
      val mem    = fromRawPtr[D](rawptr)

      Intrinsics.storeObject(rawptr, value.asInstanceOf[Object])

      GCRoots.addRoot(value.asInstanceOf[Object])

      try {
        use(mem)
      } finally {
        try {
          GCRoots.removeRoot(value.asInstanceOf[Object])
        } finally {
          libc.free(toRawPtr[D](mem))
        }
      }
    end unsafe

  end Captured
}
