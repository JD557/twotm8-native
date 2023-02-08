package microuilib

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object aliases:
  import _root_.microuilib.aliases.*
  import _root_.microuilib.structs.*
  import _root_.microuilib.unions.*

  opaque type mu_Font = Ptr[Byte]
  object mu_Font:
    given _tag: Tag[mu_Font]                           = Tag.Ptr(Tag.Byte)
    inline def apply(inline o: Ptr[Byte]): mu_Font     = o
    extension (v: mu_Font) inline def value: Ptr[Byte] = v

  opaque type mu_Id = CUnsignedInt
  object mu_Id:
    given _tag: Tag[mu_Id]                              = Tag.UInt
    inline def apply(inline o: CUnsignedInt): mu_Id     = o
    extension (v: mu_Id) inline def value: CUnsignedInt = v

  opaque type mu_Real = Float
  object mu_Real:
    given _tag: Tag[mu_Real]                       = Tag.Float
    inline def apply(inline o: Float): mu_Real     = o
    extension (v: mu_Real) inline def value: Float = v

object structs:
  import _root_.microuilib.aliases.*
  import _root_.microuilib.structs.*
  import _root_.microuilib.unions.*

  opaque type mu_BaseCommand = CStruct2[CInt, CInt]
  object mu_BaseCommand:
    given _tag: Tag[mu_BaseCommand]              = Tag.materializeCStruct2Tag[CInt, CInt]
    def apply()(using Zone): Ptr[mu_BaseCommand] = scala.scalanative.unsafe.alloc[mu_BaseCommand](1)
    def apply(`type`: CInt, size: CInt)(using Zone): Ptr[mu_BaseCommand] =
      val ____ptr = apply()
      (!____ptr).`type` = `type`
      (!____ptr).size = size
      ____ptr
    extension (struct: mu_BaseCommand)
      def `type`: CInt              = struct._1
      def type_=(value: CInt): Unit = !struct.at1 = value
      def size: CInt                = struct._2
      def size_=(value: CInt): Unit = !struct.at2 = value

  opaque type mu_ClipCommand = CStruct2[mu_BaseCommand, mu_Rect]
  object mu_ClipCommand:
    given _tag: Tag[mu_ClipCommand]              = Tag.materializeCStruct2Tag[mu_BaseCommand, mu_Rect]
    def apply()(using Zone): Ptr[mu_ClipCommand] = scala.scalanative.unsafe.alloc[mu_ClipCommand](1)
    def apply(base: mu_BaseCommand, rect: mu_Rect)(using Zone): Ptr[mu_ClipCommand] =
      val ____ptr = apply()
      (!____ptr).base = base
      (!____ptr).rect = rect
      ____ptr
    extension (struct: mu_ClipCommand)
      def base: mu_BaseCommand                = struct._1
      def base_=(value: mu_BaseCommand): Unit = !struct.at1 = value
      def rect: mu_Rect                       = struct._2
      def rect_=(value: mu_Rect): Unit        = !struct.at2 = value

  opaque type mu_Color = CStruct4[CUnsignedChar, CUnsignedChar, CUnsignedChar, CUnsignedChar]
  object mu_Color:
    given _tag: Tag[mu_Color] = Tag.materializeCStruct4Tag[CUnsignedChar, CUnsignedChar, CUnsignedChar, CUnsignedChar]
    def apply()(using Zone): Ptr[mu_Color] = scala.scalanative.unsafe.alloc[mu_Color](1)
    def apply(r: CUnsignedChar, g: CUnsignedChar, b: CUnsignedChar, a: CUnsignedChar)(using Zone): Ptr[mu_Color] =
      val ____ptr = apply()
      (!____ptr).r = r
      (!____ptr).g = g
      (!____ptr).b = b
      (!____ptr).a = a
      ____ptr
    extension (struct: mu_Color)
      def r: CUnsignedChar                = struct._1
      def r_=(value: CUnsignedChar): Unit = !struct.at1 = value
      def g: CUnsignedChar                = struct._2
      def g_=(value: CUnsignedChar): Unit = !struct.at2 = value
      def b: CUnsignedChar                = struct._3
      def b_=(value: CUnsignedChar): Unit = !struct.at3 = value
      def a: CUnsignedChar                = struct._4
      def a_=(value: CUnsignedChar): Unit = !struct.at4 = value

  opaque type mu_Container = CStruct8[Ptr[mu_Command], Ptr[mu_Command], mu_Rect, mu_Rect, mu_Vec2, mu_Vec2, CInt, CInt]
  object mu_Container:
    given _tag: Tag[mu_Container] =
      Tag.materializeCStruct8Tag[Ptr[mu_Command], Ptr[mu_Command], mu_Rect, mu_Rect, mu_Vec2, mu_Vec2, CInt, CInt]
    def apply()(using Zone): Ptr[mu_Container] = scala.scalanative.unsafe.alloc[mu_Container](1)
    def apply(
        head: Ptr[mu_Command],
        tail: Ptr[mu_Command],
        rect: mu_Rect,
        body: mu_Rect,
        content_size: mu_Vec2,
        scroll: mu_Vec2,
        zindex: CInt,
        open: CInt
    )(using Zone): Ptr[mu_Container] =
      val ____ptr = apply()
      (!____ptr).head = head
      (!____ptr).tail = tail
      (!____ptr).rect = rect
      (!____ptr).body = body
      (!____ptr).content_size = content_size
      (!____ptr).scroll = scroll
      (!____ptr).zindex = zindex
      (!____ptr).open = open
      ____ptr
    extension (struct: mu_Container)
      def head: Ptr[mu_Command]                = struct._1
      def head_=(value: Ptr[mu_Command]): Unit = !struct.at1 = value
      def tail: Ptr[mu_Command]                = struct._2
      def tail_=(value: Ptr[mu_Command]): Unit = !struct.at2 = value
      def rect: mu_Rect                        = struct._3
      def rect_=(value: mu_Rect): Unit         = !struct.at3 = value
      def body: mu_Rect                        = struct._4
      def body_=(value: mu_Rect): Unit         = !struct.at4 = value
      def content_size: mu_Vec2                = struct._5
      def content_size_=(value: mu_Vec2): Unit = !struct.at5 = value
      def scroll: mu_Vec2                      = struct._6
      def scroll_=(value: mu_Vec2): Unit       = !struct.at6 = value
      def zindex: CInt                         = struct._7
      def zindex_=(value: CInt): Unit          = !struct.at7 = value
      def open: CInt                           = struct._8
      def open_=(value: CInt): Unit            = !struct.at8 = value

  opaque type mu_Context = CArray[Byte, Nat.Digit6[Nat._2, Nat._7, Nat._0, Nat._2, Nat._0, Nat._0]]
  object mu_Context:
    opaque type Struct0 = CStruct2[CInt, CArray[CChar, Nat.Digit6[Nat._2, Nat._6, Nat._2, Nat._1, Nat._4, Nat._4]]]
    object Struct0:
      given _tag: Tag[Struct0] =
        Tag.materializeCStruct2Tag[CInt, CArray[CChar, Nat.Digit6[Nat._2, Nat._6, Nat._2, Nat._1, Nat._4, Nat._4]]]
      def apply()(using Zone): Ptr[Struct0] = scala.scalanative.unsafe.alloc[Struct0](1)
      def apply(idx: CInt, items: CArray[CChar, Nat.Digit6[Nat._2, Nat._6, Nat._2, Nat._1, Nat._4, Nat._4]])(using
          Zone
      ): Ptr[Struct0] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct0)
        def idx: CInt                                                                        = struct._1
        def idx_=(value: CInt): Unit                                                         = !struct.at1 = value
        def items: CArray[CChar, Nat.Digit6[Nat._2, Nat._6, Nat._2, Nat._1, Nat._4, Nat._4]] = struct._2
        def items_=(value: CArray[CChar, Nat.Digit6[Nat._2, Nat._6, Nat._2, Nat._1, Nat._4, Nat._4]]): Unit =
          !struct.at2 = value
    opaque type Struct1 = CStruct2[CInt, CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]]
    object Struct1:
      given _tag: Tag[Struct1] = Tag.materializeCStruct2Tag[CInt, CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]]
      def apply()(using Zone): Ptr[Struct1] = scala.scalanative.unsafe.alloc[Struct1](1)
      def apply(idx: CInt, items: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]])(using Zone): Ptr[Struct1] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct1)
        def idx: CInt                                                                   = struct._1
        def idx_=(value: CInt): Unit                                                    = !struct.at1 = value
        def items: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]                = struct._2
        def items_=(value: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]): Unit = !struct.at2 = value
    opaque type Struct2 = CStruct2[CInt, CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]]
    object Struct2:
      given _tag: Tag[Struct2] = Tag.materializeCStruct2Tag[CInt, CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]]
      def apply()(using Zone): Ptr[Struct2] = scala.scalanative.unsafe.alloc[Struct2](1)
      def apply(idx: CInt, items: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]])(using Zone): Ptr[Struct2] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct2)
        def idx: CInt                                                                   = struct._1
        def idx_=(value: CInt): Unit                                                    = !struct.at1 = value
        def items: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]                = struct._2
        def items_=(value: CArray[Ptr[mu_Container], Nat.Digit2[Nat._3, Nat._2]]): Unit = !struct.at2 = value
    opaque type Struct3 = CStruct2[CInt, CArray[mu_Rect, Nat.Digit2[Nat._3, Nat._2]]]
    object Struct3:
      given _tag: Tag[Struct3] = Tag.materializeCStruct2Tag[CInt, CArray[mu_Rect, Nat.Digit2[Nat._3, Nat._2]]]
      def apply()(using Zone): Ptr[Struct3] = scala.scalanative.unsafe.alloc[Struct3](1)
      def apply(idx: CInt, items: CArray[mu_Rect, Nat.Digit2[Nat._3, Nat._2]])(using Zone): Ptr[Struct3] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct3)
        def idx: CInt                                                         = struct._1
        def idx_=(value: CInt): Unit                                          = !struct.at1 = value
        def items: CArray[mu_Rect, Nat.Digit2[Nat._3, Nat._2]]                = struct._2
        def items_=(value: CArray[mu_Rect, Nat.Digit2[Nat._3, Nat._2]]): Unit = !struct.at2 = value
    opaque type Struct4 = CStruct2[CInt, CArray[mu_Id, Nat.Digit2[Nat._3, Nat._2]]]
    object Struct4:
      given _tag: Tag[Struct4] = Tag.materializeCStruct2Tag[CInt, CArray[mu_Id, Nat.Digit2[Nat._3, Nat._2]]]
      def apply()(using Zone): Ptr[Struct4] = scala.scalanative.unsafe.alloc[Struct4](1)
      def apply(idx: CInt, items: CArray[mu_Id, Nat.Digit2[Nat._3, Nat._2]])(using Zone): Ptr[Struct4] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct4)
        def idx: CInt                                                       = struct._1
        def idx_=(value: CInt): Unit                                        = !struct.at1 = value
        def items: CArray[mu_Id, Nat.Digit2[Nat._3, Nat._2]]                = struct._2
        def items_=(value: CArray[mu_Id, Nat.Digit2[Nat._3, Nat._2]]): Unit = !struct.at2 = value
    opaque type Struct5 = CStruct2[CInt, CArray[mu_Layout, Nat.Digit2[Nat._1, Nat._6]]]
    object Struct5:
      given _tag: Tag[Struct5] = Tag.materializeCStruct2Tag[CInt, CArray[mu_Layout, Nat.Digit2[Nat._1, Nat._6]]]
      def apply()(using Zone): Ptr[Struct5] = scala.scalanative.unsafe.alloc[Struct5](1)
      def apply(idx: CInt, items: CArray[mu_Layout, Nat.Digit2[Nat._1, Nat._6]])(using Zone): Ptr[Struct5] =
        val ____ptr = apply()
        (!____ptr).idx = idx
        (!____ptr).items = items
        ____ptr
      extension (struct: Struct5)
        def idx: CInt                                                           = struct._1
        def idx_=(value: CInt): Unit                                            = !struct.at1 = value
        def items: CArray[mu_Layout, Nat.Digit2[Nat._1, Nat._6]]                = struct._2
        def items_=(value: CArray[mu_Layout, Nat.Digit2[Nat._1, Nat._6]]): Unit = !struct.at2 = value
    given _tag: Tag[mu_Context] = Tag.CArray[CChar, Nat.Digit6[Nat._2, Nat._7, Nat._0, Nat._2, Nat._0, Nat._0]](
      Tag.Byte,
      Tag.Digit6[Nat._2, Nat._7, Nat._0, Nat._2, Nat._0, Nat._0](
        Tag.Nat2,
        Tag.Nat7,
        Tag.Nat0,
        Tag.Nat2,
        Tag.Nat0,
        Tag.Nat0
      )
    )
    def apply()(using Zone): Ptr[mu_Context] = scala.scalanative.unsafe.alloc[mu_Context](1)
    def apply(
        text_width: CFuncPtr3[mu_Font, CString, CInt, CInt],
        text_height: CFuncPtr1[mu_Font, CInt],
        draw_frame: CFuncPtr3[Ptr[mu_Context], mu_Rect, CInt, Unit],
        _style: mu_Style,
        style: Ptr[mu_Style],
        hover: mu_Id,
        focus: mu_Id,
        last_id: mu_Id,
        last_rect: mu_Rect,
        last_zindex: CInt,
        updated_focus: CInt,
        frame: CInt,
        hover_root: Ptr[mu_Container],
        next_hover_root: Ptr[mu_Container],
        scroll_target: Ptr[mu_Container],
        number_edit_buf: CArray[CChar, Nat.Digit3[Nat._1, Nat._2, Nat._7]],
        number_edit: mu_Id,
        command_list: mu_Context.Struct0,
        root_list: mu_Context.Struct1,
        container_stack: mu_Context.Struct2,
        clip_stack: mu_Context.Struct3,
        id_stack: mu_Context.Struct4,
        layout_stack: mu_Context.Struct5,
        container_pool: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]],
        containers: CArray[mu_Container, Nat.Digit2[Nat._4, Nat._8]],
        treenode_pool: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]],
        mouse_pos: mu_Vec2,
        last_mouse_pos: mu_Vec2,
        mouse_delta: mu_Vec2,
        scroll_delta: mu_Vec2,
        mouse_down: CInt,
        mouse_pressed: CInt,
        key_down: CInt,
        key_pressed: CInt,
        input_text: CArray[CChar, Nat.Digit2[Nat._3, Nat._2]]
    )(using Zone): Ptr[mu_Context] =
      val ____ptr = apply()
      (!____ptr).text_width = text_width
      (!____ptr).text_height = text_height
      (!____ptr).draw_frame = draw_frame
      (!____ptr)._style = _style
      (!____ptr).style = style
      (!____ptr).hover = hover
      (!____ptr).focus = focus
      (!____ptr).last_id = last_id
      (!____ptr).last_rect = last_rect
      (!____ptr).last_zindex = last_zindex
      (!____ptr).updated_focus = updated_focus
      (!____ptr).frame = frame
      (!____ptr).hover_root = hover_root
      (!____ptr).next_hover_root = next_hover_root
      (!____ptr).scroll_target = scroll_target
      (!____ptr).number_edit_buf = number_edit_buf
      (!____ptr).number_edit = number_edit
      (!____ptr).command_list = command_list
      (!____ptr).root_list = root_list
      (!____ptr).container_stack = container_stack
      (!____ptr).clip_stack = clip_stack
      (!____ptr).id_stack = id_stack
      (!____ptr).layout_stack = layout_stack
      (!____ptr).container_pool = container_pool
      (!____ptr).containers = containers
      (!____ptr).treenode_pool = treenode_pool
      (!____ptr).mouse_pos = mouse_pos
      (!____ptr).last_mouse_pos = last_mouse_pos
      (!____ptr).mouse_delta = mouse_delta
      (!____ptr).scroll_delta = scroll_delta
      (!____ptr).mouse_down = mouse_down
      (!____ptr).mouse_pressed = mouse_pressed
      (!____ptr).key_down = key_down
      (!____ptr).key_pressed = key_pressed
      (!____ptr).input_text = input_text
      ____ptr
    extension (struct: mu_Context)
      def text_width: CFuncPtr3[mu_Font, CString, CInt, CInt] =
        !struct.at(0).asInstanceOf[Ptr[CFuncPtr3[mu_Font, CString, CInt, CInt]]]
      def text_width_=(value: CFuncPtr3[mu_Font, CString, CInt, CInt]): Unit =
        !struct.at(0).asInstanceOf[Ptr[CFuncPtr3[mu_Font, CString, CInt, CInt]]] = value
      def text_height: CFuncPtr1[mu_Font, CInt] = !struct.at(8).asInstanceOf[Ptr[CFuncPtr1[mu_Font, CInt]]]
      def text_height_=(value: CFuncPtr1[mu_Font, CInt]): Unit =
        !struct.at(8).asInstanceOf[Ptr[CFuncPtr1[mu_Font, CInt]]] = value
      def draw_frame: CFuncPtr3[Ptr[mu_Context], mu_Rect, CInt, Unit] =
        !struct.at(16).asInstanceOf[Ptr[CFuncPtr3[Ptr[mu_Context], mu_Rect, CInt, Unit]]]
      def draw_frame_=(value: CFuncPtr3[Ptr[mu_Context], mu_Rect, CInt, Unit]): Unit =
        !struct.at(16).asInstanceOf[Ptr[CFuncPtr3[Ptr[mu_Context], mu_Rect, CInt, Unit]]] = value
      def _style: mu_Style                             = !struct.at(24).asInstanceOf[Ptr[mu_Style]]
      def _style_=(value: mu_Style): Unit              = !struct.at(24).asInstanceOf[Ptr[mu_Style]] = value
      def style: Ptr[mu_Style]                         = !struct.at(120).asInstanceOf[Ptr[Ptr[mu_Style]]]
      def style_=(value: Ptr[mu_Style]): Unit          = !struct.at(120).asInstanceOf[Ptr[Ptr[mu_Style]]] = value
      def hover: mu_Id                                 = !struct.at(128).asInstanceOf[Ptr[mu_Id]]
      def hover_=(value: mu_Id): Unit                  = !struct.at(128).asInstanceOf[Ptr[mu_Id]] = value
      def focus: mu_Id                                 = !struct.at(132).asInstanceOf[Ptr[mu_Id]]
      def focus_=(value: mu_Id): Unit                  = !struct.at(132).asInstanceOf[Ptr[mu_Id]] = value
      def last_id: mu_Id                               = !struct.at(136).asInstanceOf[Ptr[mu_Id]]
      def last_id_=(value: mu_Id): Unit                = !struct.at(136).asInstanceOf[Ptr[mu_Id]] = value
      def last_rect: mu_Rect                           = !struct.at(140).asInstanceOf[Ptr[mu_Rect]]
      def last_rect_=(value: mu_Rect): Unit            = !struct.at(140).asInstanceOf[Ptr[mu_Rect]] = value
      def last_zindex: CInt                            = !struct.at(156).asInstanceOf[Ptr[CInt]]
      def last_zindex_=(value: CInt): Unit             = !struct.at(156).asInstanceOf[Ptr[CInt]] = value
      def updated_focus: CInt                          = !struct.at(160).asInstanceOf[Ptr[CInt]]
      def updated_focus_=(value: CInt): Unit           = !struct.at(160).asInstanceOf[Ptr[CInt]] = value
      def frame: CInt                                  = !struct.at(164).asInstanceOf[Ptr[CInt]]
      def frame_=(value: CInt): Unit                   = !struct.at(164).asInstanceOf[Ptr[CInt]] = value
      def hover_root: Ptr[mu_Container]                = !struct.at(168).asInstanceOf[Ptr[Ptr[mu_Container]]]
      def hover_root_=(value: Ptr[mu_Container]): Unit = !struct.at(168).asInstanceOf[Ptr[Ptr[mu_Container]]] = value
      def next_hover_root: Ptr[mu_Container]           = !struct.at(176).asInstanceOf[Ptr[Ptr[mu_Container]]]
      def next_hover_root_=(value: Ptr[mu_Container]): Unit = !struct.at(176).asInstanceOf[Ptr[Ptr[mu_Container]]] =
        value
      def scroll_target: Ptr[mu_Container]                = !struct.at(184).asInstanceOf[Ptr[Ptr[mu_Container]]]
      def scroll_target_=(value: Ptr[mu_Container]): Unit = !struct.at(184).asInstanceOf[Ptr[Ptr[mu_Container]]] = value
      def number_edit_buf: CArray[CChar, Nat.Digit3[Nat._1, Nat._2, Nat._7]] =
        !struct.at(192).asInstanceOf[Ptr[CArray[CChar, Nat.Digit3[Nat._1, Nat._2, Nat._7]]]]
      def number_edit_buf_=(value: CArray[CChar, Nat.Digit3[Nat._1, Nat._2, Nat._7]]): Unit =
        !struct.at(192).asInstanceOf[Ptr[CArray[CChar, Nat.Digit3[Nat._1, Nat._2, Nat._7]]]] = value
      def number_edit: mu_Id                = !struct.at(320).asInstanceOf[Ptr[mu_Id]]
      def number_edit_=(value: mu_Id): Unit = !struct.at(320).asInstanceOf[Ptr[mu_Id]] = value
      def command_list: mu_Context.Struct0  = !struct.at(324).asInstanceOf[Ptr[mu_Context.Struct0]]
      def command_list_=(value: mu_Context.Struct0): Unit = !struct.at(324).asInstanceOf[Ptr[mu_Context.Struct0]] =
        value
      def root_list: mu_Context.Struct1 = !struct.at(262472).asInstanceOf[Ptr[mu_Context.Struct1]]
      def root_list_=(value: mu_Context.Struct1): Unit = !struct.at(262472).asInstanceOf[Ptr[mu_Context.Struct1]] =
        value
      def container_stack: mu_Context.Struct2 = !struct.at(262736).asInstanceOf[Ptr[mu_Context.Struct2]]
      def container_stack_=(value: mu_Context.Struct2): Unit =
        !struct.at(262736).asInstanceOf[Ptr[mu_Context.Struct2]] = value
      def clip_stack: mu_Context.Struct3 = !struct.at(263000).asInstanceOf[Ptr[mu_Context.Struct3]]
      def clip_stack_=(value: mu_Context.Struct3): Unit = !struct.at(263000).asInstanceOf[Ptr[mu_Context.Struct3]] =
        value
      def id_stack: mu_Context.Struct4                = !struct.at(263516).asInstanceOf[Ptr[mu_Context.Struct4]]
      def id_stack_=(value: mu_Context.Struct4): Unit = !struct.at(263516).asInstanceOf[Ptr[mu_Context.Struct4]] = value
      def layout_stack: mu_Context.Struct5            = !struct.at(263648).asInstanceOf[Ptr[mu_Context.Struct5]]
      def layout_stack_=(value: mu_Context.Struct5): Unit = !struct.at(263648).asInstanceOf[Ptr[mu_Context.Struct5]] =
        value
      def container_pool: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]] =
        !struct.at(265892).asInstanceOf[Ptr[CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]]]
      def container_pool_=(value: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]): Unit =
        !struct.at(265892).asInstanceOf[Ptr[CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]]] = value
      def containers: CArray[mu_Container, Nat.Digit2[Nat._4, Nat._8]] =
        !struct.at(266280).asInstanceOf[Ptr[CArray[mu_Container, Nat.Digit2[Nat._4, Nat._8]]]]
      def containers_=(value: CArray[mu_Container, Nat.Digit2[Nat._4, Nat._8]]): Unit =
        !struct.at(266280).asInstanceOf[Ptr[CArray[mu_Container, Nat.Digit2[Nat._4, Nat._8]]]] = value
      def treenode_pool: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]] =
        !struct.at(269736).asInstanceOf[Ptr[CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]]]
      def treenode_pool_=(value: CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]): Unit =
        !struct.at(269736).asInstanceOf[Ptr[CArray[mu_PoolItem, Nat.Digit2[Nat._4, Nat._8]]]] = value
      def mouse_pos: mu_Vec2                     = !struct.at(270120).asInstanceOf[Ptr[mu_Vec2]]
      def mouse_pos_=(value: mu_Vec2): Unit      = !struct.at(270120).asInstanceOf[Ptr[mu_Vec2]] = value
      def last_mouse_pos: mu_Vec2                = !struct.at(270128).asInstanceOf[Ptr[mu_Vec2]]
      def last_mouse_pos_=(value: mu_Vec2): Unit = !struct.at(270128).asInstanceOf[Ptr[mu_Vec2]] = value
      def mouse_delta: mu_Vec2                   = !struct.at(270136).asInstanceOf[Ptr[mu_Vec2]]
      def mouse_delta_=(value: mu_Vec2): Unit    = !struct.at(270136).asInstanceOf[Ptr[mu_Vec2]] = value
      def scroll_delta: mu_Vec2                  = !struct.at(270144).asInstanceOf[Ptr[mu_Vec2]]
      def scroll_delta_=(value: mu_Vec2): Unit   = !struct.at(270144).asInstanceOf[Ptr[mu_Vec2]] = value
      def mouse_down: CInt                       = !struct.at(270152).asInstanceOf[Ptr[CInt]]
      def mouse_down_=(value: CInt): Unit        = !struct.at(270152).asInstanceOf[Ptr[CInt]] = value
      def mouse_pressed: CInt                    = !struct.at(270156).asInstanceOf[Ptr[CInt]]
      def mouse_pressed_=(value: CInt): Unit     = !struct.at(270156).asInstanceOf[Ptr[CInt]] = value
      def key_down: CInt                         = !struct.at(270160).asInstanceOf[Ptr[CInt]]
      def key_down_=(value: CInt): Unit          = !struct.at(270160).asInstanceOf[Ptr[CInt]] = value
      def key_pressed: CInt                      = !struct.at(270164).asInstanceOf[Ptr[CInt]]
      def key_pressed_=(value: CInt): Unit       = !struct.at(270164).asInstanceOf[Ptr[CInt]] = value
      def input_text: CArray[CChar, Nat.Digit2[Nat._3, Nat._2]] =
        !struct.at(270168).asInstanceOf[Ptr[CArray[CChar, Nat.Digit2[Nat._3, Nat._2]]]]
      def input_text_=(value: CArray[CChar, Nat.Digit2[Nat._3, Nat._2]]): Unit =
        !struct.at(270168).asInstanceOf[Ptr[CArray[CChar, Nat.Digit2[Nat._3, Nat._2]]]] = value

  opaque type mu_IconCommand = CStruct4[mu_BaseCommand, mu_Rect, CInt, mu_Color]
  object mu_IconCommand:
    given _tag: Tag[mu_IconCommand]              = Tag.materializeCStruct4Tag[mu_BaseCommand, mu_Rect, CInt, mu_Color]
    def apply()(using Zone): Ptr[mu_IconCommand] = scala.scalanative.unsafe.alloc[mu_IconCommand](1)
    def apply(base: mu_BaseCommand, rect: mu_Rect, id: CInt, color: mu_Color)(using Zone): Ptr[mu_IconCommand] =
      val ____ptr = apply()
      (!____ptr).base = base
      (!____ptr).rect = rect
      (!____ptr).id = id
      (!____ptr).color = color
      ____ptr
    extension (struct: mu_IconCommand)
      def base: mu_BaseCommand                = struct._1
      def base_=(value: mu_BaseCommand): Unit = !struct.at1 = value
      def rect: mu_Rect                       = struct._2
      def rect_=(value: mu_Rect): Unit        = !struct.at2 = value
      def id: CInt                            = struct._3
      def id_=(value: CInt): Unit             = !struct.at3 = value
      def color: mu_Color                     = struct._4
      def color_=(value: mu_Color): Unit      = !struct.at4 = value

  opaque type mu_JumpCommand = CStruct2[mu_BaseCommand, Ptr[Byte]]
  object mu_JumpCommand:
    given _tag: Tag[mu_JumpCommand]              = Tag.materializeCStruct2Tag[mu_BaseCommand, Ptr[Byte]]
    def apply()(using Zone): Ptr[mu_JumpCommand] = scala.scalanative.unsafe.alloc[mu_JumpCommand](1)
    def apply(base: mu_BaseCommand, dst: Ptr[Byte])(using Zone): Ptr[mu_JumpCommand] =
      val ____ptr = apply()
      (!____ptr).base = base
      (!____ptr).dst = dst
      ____ptr
    extension (struct: mu_JumpCommand)
      def base: mu_BaseCommand                = struct._1
      def base_=(value: mu_BaseCommand): Unit = !struct.at1 = value
      def dst: Ptr[Byte]                      = struct._2
      def dst_=(value: Ptr[Byte]): Unit       = !struct.at2 = value

  opaque type mu_Layout = CStruct11[
    mu_Rect,
    mu_Rect,
    mu_Vec2,
    mu_Vec2,
    mu_Vec2,
    CArray[CInt, Nat.Digit2[Nat._1, Nat._6]],
    CInt,
    CInt,
    CInt,
    CInt,
    CInt
  ]
  object mu_Layout:
    given _tag: Tag[mu_Layout] = Tag.materializeCStruct11Tag[mu_Rect, mu_Rect, mu_Vec2, mu_Vec2, mu_Vec2, CArray[
      CInt,
      Nat.Digit2[Nat._1, Nat._6]
    ], CInt, CInt, CInt, CInt, CInt]
    def apply()(using Zone): Ptr[mu_Layout] = scala.scalanative.unsafe.alloc[mu_Layout](1)
    def apply(
        body: mu_Rect,
        next: mu_Rect,
        position: mu_Vec2,
        size: mu_Vec2,
        max: mu_Vec2,
        widths: CArray[CInt, Nat.Digit2[Nat._1, Nat._6]],
        items: CInt,
        item_index: CInt,
        next_row: CInt,
        next_type: CInt,
        indent: CInt
    )(using Zone): Ptr[mu_Layout] =
      val ____ptr = apply()
      (!____ptr).body = body
      (!____ptr).next = next
      (!____ptr).position = position
      (!____ptr).size = size
      (!____ptr).max = max
      (!____ptr).widths = widths
      (!____ptr).items = items
      (!____ptr).item_index = item_index
      (!____ptr).next_row = next_row
      (!____ptr).next_type = next_type
      (!____ptr).indent = indent
      ____ptr
    extension (struct: mu_Layout)
      def body: mu_Rect                                                   = struct._1
      def body_=(value: mu_Rect): Unit                                    = !struct.at1 = value
      def next: mu_Rect                                                   = struct._2
      def next_=(value: mu_Rect): Unit                                    = !struct.at2 = value
      def position: mu_Vec2                                               = struct._3
      def position_=(value: mu_Vec2): Unit                                = !struct.at3 = value
      def size: mu_Vec2                                                   = struct._4
      def size_=(value: mu_Vec2): Unit                                    = !struct.at4 = value
      def max: mu_Vec2                                                    = struct._5
      def max_=(value: mu_Vec2): Unit                                     = !struct.at5 = value
      def widths: CArray[CInt, Nat.Digit2[Nat._1, Nat._6]]                = struct._6
      def widths_=(value: CArray[CInt, Nat.Digit2[Nat._1, Nat._6]]): Unit = !struct.at6 = value
      def items: CInt                                                     = struct._7
      def items_=(value: CInt): Unit                                      = !struct.at7 = value
      def item_index: CInt                                                = struct._8
      def item_index_=(value: CInt): Unit                                 = !struct.at8 = value
      def next_row: CInt                                                  = struct._9
      def next_row_=(value: CInt): Unit                                   = !struct.at9 = value
      def next_type: CInt                                                 = struct._10
      def next_type_=(value: CInt): Unit                                  = !struct.at10 = value
      def indent: CInt                                                    = struct._11
      def indent_=(value: CInt): Unit                                     = !struct.at11 = value

  opaque type mu_PoolItem = CStruct2[mu_Id, CInt]
  object mu_PoolItem:
    given _tag: Tag[mu_PoolItem]              = Tag.materializeCStruct2Tag[mu_Id, CInt]
    def apply()(using Zone): Ptr[mu_PoolItem] = scala.scalanative.unsafe.alloc[mu_PoolItem](1)
    def apply(id: mu_Id, last_update: CInt)(using Zone): Ptr[mu_PoolItem] =
      val ____ptr = apply()
      (!____ptr).id = id
      (!____ptr).last_update = last_update
      ____ptr
    extension (struct: mu_PoolItem)
      def id: mu_Id                        = struct._1
      def id_=(value: mu_Id): Unit         = !struct.at1 = value
      def last_update: CInt                = struct._2
      def last_update_=(value: CInt): Unit = !struct.at2 = value

  opaque type mu_Rect = CStruct4[CInt, CInt, CInt, CInt]
  object mu_Rect:
    given _tag: Tag[mu_Rect]              = Tag.materializeCStruct4Tag[CInt, CInt, CInt, CInt]
    def apply()(using Zone): Ptr[mu_Rect] = scala.scalanative.unsafe.alloc[mu_Rect](1)
    def apply(x: CInt, y: CInt, w: CInt, h: CInt)(using Zone): Ptr[mu_Rect] =
      val ____ptr = apply()
      (!____ptr).x = x
      (!____ptr).y = y
      (!____ptr).w = w
      (!____ptr).h = h
      ____ptr
    extension (struct: mu_Rect)
      def x: CInt                = struct._1
      def x_=(value: CInt): Unit = !struct.at1 = value
      def y: CInt                = struct._2
      def y_=(value: CInt): Unit = !struct.at2 = value
      def w: CInt                = struct._3
      def w_=(value: CInt): Unit = !struct.at3 = value
      def h: CInt                = struct._4
      def h_=(value: CInt): Unit = !struct.at4 = value

  opaque type mu_RectCommand = CStruct3[mu_BaseCommand, mu_Rect, mu_Color]
  object mu_RectCommand:
    given _tag: Tag[mu_RectCommand]              = Tag.materializeCStruct3Tag[mu_BaseCommand, mu_Rect, mu_Color]
    def apply()(using Zone): Ptr[mu_RectCommand] = scala.scalanative.unsafe.alloc[mu_RectCommand](1)
    def apply(base: mu_BaseCommand, rect: mu_Rect, color: mu_Color)(using Zone): Ptr[mu_RectCommand] =
      val ____ptr = apply()
      (!____ptr).base = base
      (!____ptr).rect = rect
      (!____ptr).color = color
      ____ptr
    extension (struct: mu_RectCommand)
      def base: mu_BaseCommand                = struct._1
      def base_=(value: mu_BaseCommand): Unit = !struct.at1 = value
      def rect: mu_Rect                       = struct._2
      def rect_=(value: mu_Rect): Unit        = !struct.at2 = value
      def color: mu_Color                     = struct._3
      def color_=(value: mu_Color): Unit      = !struct.at3 = value

  opaque type mu_Style =
    CStruct9[mu_Font, mu_Vec2, CInt, CInt, CInt, CInt, CInt, CInt, CArray[mu_Color, Nat.Digit2[Nat._1, Nat._4]]]
  object mu_Style:
    given _tag: Tag[mu_Style] = Tag.materializeCStruct9Tag[mu_Font, mu_Vec2, CInt, CInt, CInt, CInt, CInt, CInt, CArray[
      mu_Color,
      Nat.Digit2[Nat._1, Nat._4]
    ]]
    def apply()(using Zone): Ptr[mu_Style] = scala.scalanative.unsafe.alloc[mu_Style](1)
    def apply(
        font: mu_Font,
        size: mu_Vec2,
        padding: CInt,
        spacing: CInt,
        indent: CInt,
        title_height: CInt,
        scrollbar_size: CInt,
        thumb_size: CInt,
        colors: CArray[mu_Color, Nat.Digit2[Nat._1, Nat._4]]
    )(using Zone): Ptr[mu_Style] =
      val ____ptr = apply()
      (!____ptr).font = font
      (!____ptr).size = size
      (!____ptr).padding = padding
      (!____ptr).spacing = spacing
      (!____ptr).indent = indent
      (!____ptr).title_height = title_height
      (!____ptr).scrollbar_size = scrollbar_size
      (!____ptr).thumb_size = thumb_size
      (!____ptr).colors = colors
      ____ptr
    extension (struct: mu_Style)
      def font: mu_Font                                                       = struct._1
      def font_=(value: mu_Font): Unit                                        = !struct.at1 = value
      def size: mu_Vec2                                                       = struct._2
      def size_=(value: mu_Vec2): Unit                                        = !struct.at2 = value
      def padding: CInt                                                       = struct._3
      def padding_=(value: CInt): Unit                                        = !struct.at3 = value
      def spacing: CInt                                                       = struct._4
      def spacing_=(value: CInt): Unit                                        = !struct.at4 = value
      def indent: CInt                                                        = struct._5
      def indent_=(value: CInt): Unit                                         = !struct.at5 = value
      def title_height: CInt                                                  = struct._6
      def title_height_=(value: CInt): Unit                                   = !struct.at6 = value
      def scrollbar_size: CInt                                                = struct._7
      def scrollbar_size_=(value: CInt): Unit                                 = !struct.at7 = value
      def thumb_size: CInt                                                    = struct._8
      def thumb_size_=(value: CInt): Unit                                     = !struct.at8 = value
      def colors: CArray[mu_Color, Nat.Digit2[Nat._1, Nat._4]]                = struct._9
      def colors_=(value: CArray[mu_Color, Nat.Digit2[Nat._1, Nat._4]]): Unit = !struct.at9 = value

  opaque type mu_TextCommand = CStruct5[mu_BaseCommand, mu_Font, mu_Vec2, mu_Color, CArray[CChar, Nat._1]]
  object mu_TextCommand:
    given _tag: Tag[mu_TextCommand] =
      Tag.materializeCStruct5Tag[mu_BaseCommand, mu_Font, mu_Vec2, mu_Color, CArray[CChar, Nat._1]]
    def apply()(using Zone): Ptr[mu_TextCommand] = scala.scalanative.unsafe.alloc[mu_TextCommand](1)
    def apply(base: mu_BaseCommand, font: mu_Font, pos: mu_Vec2, color: mu_Color, str: CArray[CChar, Nat._1])(using
        Zone
    ): Ptr[mu_TextCommand] =
      val ____ptr = apply()
      (!____ptr).base = base
      (!____ptr).font = font
      (!____ptr).pos = pos
      (!____ptr).color = color
      (!____ptr).str = str
      ____ptr
    extension (struct: mu_TextCommand)
      def base: mu_BaseCommand                      = struct._1
      def base_=(value: mu_BaseCommand): Unit       = !struct.at1 = value
      def font: mu_Font                             = struct._2
      def font_=(value: mu_Font): Unit              = !struct.at2 = value
      def pos: mu_Vec2                              = struct._3
      def pos_=(value: mu_Vec2): Unit               = !struct.at3 = value
      def color: mu_Color                           = struct._4
      def color_=(value: mu_Color): Unit            = !struct.at4 = value
      def str: CArray[CChar, Nat._1]                = struct._5
      def str_=(value: CArray[CChar, Nat._1]): Unit = !struct.at5 = value

  opaque type mu_Vec2 = CStruct2[CInt, CInt]
  object mu_Vec2:
    given _tag: Tag[mu_Vec2]              = Tag.materializeCStruct2Tag[CInt, CInt]
    def apply()(using Zone): Ptr[mu_Vec2] = scala.scalanative.unsafe.alloc[mu_Vec2](1)
    def apply(x: CInt, y: CInt)(using Zone): Ptr[mu_Vec2] =
      val ____ptr = apply()
      (!____ptr).x = x
      (!____ptr).y = y
      ____ptr
    extension (struct: mu_Vec2)
      def x: CInt                = struct._1
      def x_=(value: CInt): Unit = !struct.at1 = value
      def y: CInt                = struct._2
      def y_=(value: CInt): Unit = !struct.at2 = value

object unions:
  import _root_.microuilib.aliases.*
  import _root_.microuilib.structs.*
  import _root_.microuilib.unions.*

  opaque type mu_Command = CArray[Byte, Nat.Digit2[Nat._3, Nat._2]]
  object mu_Command:
    given _tag: Tag[mu_Command] =
      Tag.CArray[CChar, Nat.Digit2[Nat._3, Nat._2]](Tag.Byte, Tag.Digit2[Nat._3, Nat._2](Tag.Nat3, Tag.Nat2))
    def apply()(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      ___ptr
    @scala.annotation.targetName("apply_type")
    def apply(`type`: CInt)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[CInt]].update(0, `type`)
      ___ptr
    @scala.annotation.targetName("apply_base")
    def apply(base: mu_BaseCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_BaseCommand]].update(0, base)
      ___ptr
    @scala.annotation.targetName("apply_jump")
    def apply(jump: mu_JumpCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_JumpCommand]].update(0, jump)
      ___ptr
    @scala.annotation.targetName("apply_clip")
    def apply(clip: mu_ClipCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_ClipCommand]].update(0, clip)
      ___ptr
    @scala.annotation.targetName("apply_rect")
    def apply(rect: mu_RectCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_RectCommand]].update(0, rect)
      ___ptr
    @scala.annotation.targetName("apply_text")
    def apply(text: mu_TextCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_TextCommand]].update(0, text)
      ___ptr
    @scala.annotation.targetName("apply_icon")
    def apply(icon: mu_IconCommand)(using Zone): Ptr[mu_Command] =
      val ___ptr = alloc[mu_Command](1)
      val un     = !___ptr
      un.at(0).asInstanceOf[Ptr[mu_IconCommand]].update(0, icon)
      ___ptr
    extension (struct: mu_Command)
      def `type`: CInt                        = !struct.at(0).asInstanceOf[Ptr[CInt]]
      def type_=(value: CInt): Unit           = !struct.at(0).asInstanceOf[Ptr[CInt]] = value
      def base: mu_BaseCommand                = !struct.at(0).asInstanceOf[Ptr[mu_BaseCommand]]
      def base_=(value: mu_BaseCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_BaseCommand]] = value
      def jump: mu_JumpCommand                = !struct.at(0).asInstanceOf[Ptr[mu_JumpCommand]]
      def jump_=(value: mu_JumpCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_JumpCommand]] = value
      def clip: mu_ClipCommand                = !struct.at(0).asInstanceOf[Ptr[mu_ClipCommand]]
      def clip_=(value: mu_ClipCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_ClipCommand]] = value
      def rect: mu_RectCommand                = !struct.at(0).asInstanceOf[Ptr[mu_RectCommand]]
      def rect_=(value: mu_RectCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_RectCommand]] = value
      def text: mu_TextCommand                = !struct.at(0).asInstanceOf[Ptr[mu_TextCommand]]
      def text_=(value: mu_TextCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_TextCommand]] = value
      def icon: mu_IconCommand                = !struct.at(0).asInstanceOf[Ptr[mu_IconCommand]]
      def icon_=(value: mu_IconCommand): Unit = !struct.at(0).asInstanceOf[Ptr[mu_IconCommand]] = value

@extern
private[microuilib] object extern_functions:
  import _root_.microuilib.aliases.*
  import _root_.microuilib.structs.*
  import _root_.microuilib.unions.*

  private[microuilib] def __sn_wrap_microuilib_mu_begin_window_ex(
      ctx: Ptr[mu_Context],
      title: CString,
      rect: Ptr[mu_Rect],
      opt: CInt
  ): CInt = extern

  private[microuilib] def __sn_wrap_microuilib_mu_check_clip(ctx: Ptr[mu_Context], r: Ptr[mu_Rect]): CInt = extern

  private[microuilib] def __sn_wrap_microuilib_mu_color(
      r: CInt,
      g: CInt,
      b: CInt,
      a: CInt,
      __return: Ptr[mu_Color]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_box(
      ctx: Ptr[mu_Context],
      rect: Ptr[mu_Rect],
      color: Ptr[mu_Color]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_control_frame(
      ctx: Ptr[mu_Context],
      id: mu_Id,
      rect: Ptr[mu_Rect],
      colorid: CInt,
      opt: CInt
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_control_text(
      ctx: Ptr[mu_Context],
      str: CString,
      rect: Ptr[mu_Rect],
      colorid: CInt,
      opt: CInt
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_icon(
      ctx: Ptr[mu_Context],
      id: CInt,
      rect: Ptr[mu_Rect],
      color: Ptr[mu_Color]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_rect(
      ctx: Ptr[mu_Context],
      rect: Ptr[mu_Rect],
      color: Ptr[mu_Color]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_draw_text(
      ctx: Ptr[mu_Context],
      font: mu_Font,
      str: CString,
      len: CInt,
      pos: Ptr[mu_Vec2],
      color: Ptr[mu_Color]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_get_clip_rect(ctx: Ptr[mu_Context], __return: Ptr[mu_Rect]): Unit =
    extern

  private[microuilib] def __sn_wrap_microuilib_mu_layout_next(ctx: Ptr[mu_Context], __return: Ptr[mu_Rect]): Unit =
    extern

  private[microuilib] def __sn_wrap_microuilib_mu_layout_set_next(
      ctx: Ptr[mu_Context],
      r: Ptr[mu_Rect],
      relative: CInt
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_mouse_over(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): CInt = extern

  private[microuilib] def __sn_wrap_microuilib_mu_push_clip_rect(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): Unit =
    extern

  private[microuilib] def __sn_wrap_microuilib_mu_rect(
      x: CInt,
      y: CInt,
      w: CInt,
      h: CInt,
      __return: Ptr[mu_Rect]
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_set_clip(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_textbox_raw(
      ctx: Ptr[mu_Context],
      buf: CString,
      bufsz: CInt,
      id: mu_Id,
      r: Ptr[mu_Rect],
      opt: CInt
  ): CInt = extern

  private[microuilib] def __sn_wrap_microuilib_mu_update_control(
      ctx: Ptr[mu_Context],
      id: mu_Id,
      rect: Ptr[mu_Rect],
      opt: CInt
  ): Unit = extern

  private[microuilib] def __sn_wrap_microuilib_mu_vec2(x: CInt, y: CInt, __return: Ptr[mu_Vec2]): Unit = extern

  def mu_begin(ctx: Ptr[mu_Context]): Unit = extern

  def mu_begin_panel_ex(ctx: Ptr[mu_Context], name: CString, opt: CInt): Unit = extern

  def mu_begin_popup(ctx: Ptr[mu_Context], name: CString): CInt = extern

  def mu_begin_treenode_ex(ctx: Ptr[mu_Context], label: CString, opt: CInt): CInt = extern

  def mu_bring_to_front(ctx: Ptr[mu_Context], cnt: Ptr[mu_Container]): Unit = extern

  def mu_button_ex(ctx: Ptr[mu_Context], label: CString, icon: CInt, opt: CInt): CInt = extern

  def mu_checkbox(ctx: Ptr[mu_Context], label: CString, state: Ptr[CInt]): CInt = extern

  def mu_end(ctx: Ptr[mu_Context]): Unit = extern

  def mu_end_panel(ctx: Ptr[mu_Context]): Unit = extern

  def mu_end_popup(ctx: Ptr[mu_Context]): Unit = extern

  def mu_end_treenode(ctx: Ptr[mu_Context]): Unit = extern

  def mu_end_window(ctx: Ptr[mu_Context]): Unit = extern

  def mu_get_container(ctx: Ptr[mu_Context], name: CString): Ptr[mu_Container] = extern

  def mu_get_current_container(ctx: Ptr[mu_Context]): Ptr[mu_Container] = extern

  def mu_get_id(ctx: Ptr[mu_Context], data: Ptr[Byte], size: CInt): mu_Id = extern

  def mu_header_ex(ctx: Ptr[mu_Context], label: CString, opt: CInt): CInt = extern

  def mu_init(ctx: Ptr[mu_Context]): Unit = extern

  def mu_input_keydown(ctx: Ptr[mu_Context], key: CInt): Unit = extern

  def mu_input_keyup(ctx: Ptr[mu_Context], key: CInt): Unit = extern

  def mu_input_mousedown(ctx: Ptr[mu_Context], x: CInt, y: CInt, btn: CInt): Unit = extern

  def mu_input_mousemove(ctx: Ptr[mu_Context], x: CInt, y: CInt): Unit = extern

  def mu_input_mouseup(ctx: Ptr[mu_Context], x: CInt, y: CInt, btn: CInt): Unit = extern

  def mu_input_scroll(ctx: Ptr[mu_Context], x: CInt, y: CInt): Unit = extern

  def mu_input_text(ctx: Ptr[mu_Context], text: CString): Unit = extern

  def mu_label(ctx: Ptr[mu_Context], text: CString): Unit = extern

  def mu_layout_begin_column(ctx: Ptr[mu_Context]): Unit = extern

  def mu_layout_end_column(ctx: Ptr[mu_Context]): Unit = extern

  def mu_layout_height(ctx: Ptr[mu_Context], height: CInt): Unit = extern

  def mu_layout_row(ctx: Ptr[mu_Context], items: CInt, widths: Ptr[CInt], height: CInt): Unit = extern

  def mu_layout_width(ctx: Ptr[mu_Context], width: CInt): Unit = extern

  def mu_next_command(ctx: Ptr[mu_Context], cmd: Ptr[Ptr[mu_Command]]): CInt = extern

  def mu_number_ex(ctx: Ptr[mu_Context], value: Ptr[mu_Real], step: mu_Real, fmt: CString, opt: CInt): CInt = extern

  def mu_open_popup(ctx: Ptr[mu_Context], name: CString): Unit = extern

  def mu_pool_get(ctx: Ptr[mu_Context], items: Ptr[mu_PoolItem], len: CInt, id: mu_Id): CInt = extern

  def mu_pool_init(ctx: Ptr[mu_Context], items: Ptr[mu_PoolItem], len: CInt, id: mu_Id): CInt = extern

  def mu_pool_update(ctx: Ptr[mu_Context], items: Ptr[mu_PoolItem], idx: CInt): Unit = extern

  def mu_pop_clip_rect(ctx: Ptr[mu_Context]): Unit = extern

  def mu_pop_id(ctx: Ptr[mu_Context]): Unit = extern

  def mu_push_command(ctx: Ptr[mu_Context], `type`: CInt, size: CInt): Ptr[mu_Command] = extern

  def mu_push_id(ctx: Ptr[mu_Context], data: Ptr[Byte], size: CInt): Unit = extern

  def mu_set_focus(ctx: Ptr[mu_Context], id: mu_Id): Unit = extern

  def mu_slider_ex(
      ctx: Ptr[mu_Context],
      value: Ptr[mu_Real],
      low: mu_Real,
      high: mu_Real,
      step: mu_Real,
      fmt: CString,
      opt: CInt
  ): CInt = extern

  def mu_text(ctx: Ptr[mu_Context], text: CString): Unit = extern

  def mu_textbox_ex(ctx: Ptr[mu_Context], buf: CString, bufsz: CInt, opt: CInt): CInt = extern

object functions:
  import _root_.microuilib.aliases.*
  import _root_.microuilib.structs.*
  import _root_.microuilib.unions.*

  import extern_functions.*
  export extern_functions.*

  def mu_begin_window_ex(ctx: Ptr[mu_Context], title: CString, rect: Ptr[mu_Rect], opt: CInt): CInt =
    __sn_wrap_microuilib_mu_begin_window_ex(ctx, title, rect, opt)

  def mu_begin_window_ex(ctx: Ptr[mu_Context], title: CString, rect: mu_Rect, opt: CInt)(using Zone): CInt =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_begin_window_ex(ctx, title, (__ptr_0 + 0), opt)

  def mu_check_clip(ctx: Ptr[mu_Context], r: mu_Rect)(using Zone): CInt =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = r
    __sn_wrap_microuilib_mu_check_clip(ctx, (__ptr_0 + 0))

  def mu_check_clip(ctx: Ptr[mu_Context], r: Ptr[mu_Rect]): CInt =
    __sn_wrap_microuilib_mu_check_clip(ctx, r)

  def mu_color(r: CInt, g: CInt, b: CInt, a: CInt)(using Zone): mu_Color =
    val __ptr_0: Ptr[mu_Color] = alloc[mu_Color](1)
    __sn_wrap_microuilib_mu_color(r, g, b, a, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def mu_color(r: CInt, g: CInt, b: CInt, a: CInt)(__return: Ptr[mu_Color]): Unit =
    __sn_wrap_microuilib_mu_color(r, g, b, a, __return)

  def mu_draw_box(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect], color: Ptr[mu_Color]): Unit =
    __sn_wrap_microuilib_mu_draw_box(ctx, rect, color)

  def mu_draw_box(ctx: Ptr[mu_Context], rect: mu_Rect, color: mu_Color)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect]  = alloc[mu_Rect](1)
    val __ptr_1: Ptr[mu_Color] = alloc[mu_Color](1)
    !(__ptr_0 + 0) = rect
    !(__ptr_1 + 0) = color
    __sn_wrap_microuilib_mu_draw_box(ctx, (__ptr_0 + 0), (__ptr_1 + 0))

  def mu_draw_control_frame(ctx: Ptr[mu_Context], id: mu_Id, rect: Ptr[mu_Rect], colorid: CInt, opt: CInt): Unit =
    __sn_wrap_microuilib_mu_draw_control_frame(ctx, id, rect, colorid, opt)

  def mu_draw_control_frame(ctx: Ptr[mu_Context], id: mu_Id, rect: mu_Rect, colorid: CInt, opt: CInt)(using
      Zone
  ): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_draw_control_frame(ctx, id, (__ptr_0 + 0), colorid, opt)

  def mu_draw_control_text(ctx: Ptr[mu_Context], str: CString, rect: Ptr[mu_Rect], colorid: CInt, opt: CInt): Unit =
    __sn_wrap_microuilib_mu_draw_control_text(ctx, str, rect, colorid, opt)

  def mu_draw_control_text(ctx: Ptr[mu_Context], str: CString, rect: mu_Rect, colorid: CInt, opt: CInt)(using
      Zone
  ): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_draw_control_text(ctx, str, (__ptr_0 + 0), colorid, opt)

  def mu_draw_icon(ctx: Ptr[mu_Context], id: CInt, rect: mu_Rect, color: mu_Color)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect]  = alloc[mu_Rect](1)
    val __ptr_1: Ptr[mu_Color] = alloc[mu_Color](1)
    !(__ptr_0 + 0) = rect
    !(__ptr_1 + 0) = color
    __sn_wrap_microuilib_mu_draw_icon(ctx, id, (__ptr_0 + 0), (__ptr_1 + 0))

  def mu_draw_icon(ctx: Ptr[mu_Context], id: CInt, rect: Ptr[mu_Rect], color: Ptr[mu_Color]): Unit =
    __sn_wrap_microuilib_mu_draw_icon(ctx, id, rect, color)

  def mu_draw_rect(ctx: Ptr[mu_Context], rect: mu_Rect, color: mu_Color)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect]  = alloc[mu_Rect](1)
    val __ptr_1: Ptr[mu_Color] = alloc[mu_Color](1)
    !(__ptr_0 + 0) = rect
    !(__ptr_1 + 0) = color
    __sn_wrap_microuilib_mu_draw_rect(ctx, (__ptr_0 + 0), (__ptr_1 + 0))

  def mu_draw_rect(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect], color: Ptr[mu_Color]): Unit =
    __sn_wrap_microuilib_mu_draw_rect(ctx, rect, color)

  def mu_draw_text(
      ctx: Ptr[mu_Context],
      font: mu_Font,
      str: CString,
      len: CInt,
      pos: Ptr[mu_Vec2],
      color: Ptr[mu_Color]
  ): Unit =
    __sn_wrap_microuilib_mu_draw_text(ctx, font, str, len, pos, color)

  def mu_draw_text(ctx: Ptr[mu_Context], font: mu_Font, str: CString, len: CInt, pos: mu_Vec2, color: mu_Color)(using
      Zone
  ): Unit =
    val __ptr_0: Ptr[mu_Vec2]  = alloc[mu_Vec2](1)
    val __ptr_1: Ptr[mu_Color] = alloc[mu_Color](1)
    !(__ptr_0 + 0) = pos
    !(__ptr_1 + 0) = color
    __sn_wrap_microuilib_mu_draw_text(ctx, font, str, len, (__ptr_0 + 0), (__ptr_1 + 0))

  def mu_get_clip_rect(ctx: Ptr[mu_Context])(__return: Ptr[mu_Rect]): Unit =
    __sn_wrap_microuilib_mu_get_clip_rect(ctx, __return)

  def mu_get_clip_rect(ctx: Ptr[mu_Context])(using Zone): mu_Rect =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    __sn_wrap_microuilib_mu_get_clip_rect(ctx, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def mu_layout_next(ctx: Ptr[mu_Context])(__return: Ptr[mu_Rect]): Unit =
    __sn_wrap_microuilib_mu_layout_next(ctx, __return)

  def mu_layout_next(ctx: Ptr[mu_Context])(using Zone): mu_Rect =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    __sn_wrap_microuilib_mu_layout_next(ctx, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def mu_layout_set_next(ctx: Ptr[mu_Context], r: mu_Rect, relative: CInt)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = r
    __sn_wrap_microuilib_mu_layout_set_next(ctx, (__ptr_0 + 0), relative)

  def mu_layout_set_next(ctx: Ptr[mu_Context], r: Ptr[mu_Rect], relative: CInt): Unit =
    __sn_wrap_microuilib_mu_layout_set_next(ctx, r, relative)

  def mu_mouse_over(ctx: Ptr[mu_Context], rect: mu_Rect)(using Zone): CInt =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_mouse_over(ctx, (__ptr_0 + 0))

  def mu_mouse_over(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): CInt =
    __sn_wrap_microuilib_mu_mouse_over(ctx, rect)

  def mu_push_clip_rect(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): Unit =
    __sn_wrap_microuilib_mu_push_clip_rect(ctx, rect)

  def mu_push_clip_rect(ctx: Ptr[mu_Context], rect: mu_Rect)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_push_clip_rect(ctx, (__ptr_0 + 0))

  def mu_rect(x: CInt, y: CInt, w: CInt, h: CInt)(__return: Ptr[mu_Rect]): Unit =
    __sn_wrap_microuilib_mu_rect(x, y, w, h, __return)

  def mu_rect(x: CInt, y: CInt, w: CInt, h: CInt)(using Zone): mu_Rect =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    __sn_wrap_microuilib_mu_rect(x, y, w, h, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def mu_set_clip(ctx: Ptr[mu_Context], rect: mu_Rect)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_set_clip(ctx, (__ptr_0 + 0))

  def mu_set_clip(ctx: Ptr[mu_Context], rect: Ptr[mu_Rect]): Unit =
    __sn_wrap_microuilib_mu_set_clip(ctx, rect)

  def mu_textbox_raw(ctx: Ptr[mu_Context], buf: CString, bufsz: CInt, id: mu_Id, r: mu_Rect, opt: CInt)(using
      Zone
  ): CInt =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = r
    __sn_wrap_microuilib_mu_textbox_raw(ctx, buf, bufsz, id, (__ptr_0 + 0), opt)

  def mu_textbox_raw(ctx: Ptr[mu_Context], buf: CString, bufsz: CInt, id: mu_Id, r: Ptr[mu_Rect], opt: CInt): CInt =
    __sn_wrap_microuilib_mu_textbox_raw(ctx, buf, bufsz, id, r, opt)

  def mu_update_control(ctx: Ptr[mu_Context], id: mu_Id, rect: mu_Rect, opt: CInt)(using Zone): Unit =
    val __ptr_0: Ptr[mu_Rect] = alloc[mu_Rect](1)
    !(__ptr_0 + 0) = rect
    __sn_wrap_microuilib_mu_update_control(ctx, id, (__ptr_0 + 0), opt)

  def mu_update_control(ctx: Ptr[mu_Context], id: mu_Id, rect: Ptr[mu_Rect], opt: CInt): Unit =
    __sn_wrap_microuilib_mu_update_control(ctx, id, rect, opt)

  def mu_vec2(x: CInt, y: CInt)(using Zone): mu_Vec2 =
    val __ptr_0: Ptr[mu_Vec2] = alloc[mu_Vec2](1)
    __sn_wrap_microuilib_mu_vec2(x, y, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def mu_vec2(x: CInt, y: CInt)(__return: Ptr[mu_Vec2]): Unit =
    __sn_wrap_microuilib_mu_vec2(x, y, __return)

object constants:
  val MU_CLIP_PART: CUnsignedInt = 1.toUInt
  val MU_CLIP_ALL: CUnsignedInt  = 2.toUInt

  val MU_RES_ACTIVE: CUnsignedInt = 1.toUInt
  val MU_RES_SUBMIT: CUnsignedInt = 2.toUInt
  val MU_RES_CHANGE: CUnsignedInt = 4.toUInt

  val MU_COMMAND_JUMP: CUnsignedInt = 1.toUInt
  val MU_COMMAND_CLIP: CUnsignedInt = 2.toUInt
  val MU_COMMAND_RECT: CUnsignedInt = 3.toUInt
  val MU_COMMAND_TEXT: CUnsignedInt = 4.toUInt
  val MU_COMMAND_ICON: CUnsignedInt = 5.toUInt
  val MU_COMMAND_MAX: CUnsignedInt  = 6.toUInt

  val MU_KEY_SHIFT: CUnsignedInt     = 1.toUInt
  val MU_KEY_CTRL: CUnsignedInt      = 2.toUInt
  val MU_KEY_ALT: CUnsignedInt       = 4.toUInt
  val MU_KEY_BACKSPACE: CUnsignedInt = 8.toUInt
  val MU_KEY_RETURN: CUnsignedInt    = 16.toUInt

  val MU_MOUSE_LEFT: CUnsignedInt   = 1.toUInt
  val MU_MOUSE_RIGHT: CUnsignedInt  = 2.toUInt
  val MU_MOUSE_MIDDLE: CUnsignedInt = 4.toUInt

  val MU_ICON_CLOSE: CUnsignedInt     = 1.toUInt
  val MU_ICON_CHECK: CUnsignedInt     = 2.toUInt
  val MU_ICON_COLLAPSED: CUnsignedInt = 3.toUInt
  val MU_ICON_EXPANDED: CUnsignedInt  = 4.toUInt
  val MU_ICON_MAX: CUnsignedInt       = 5.toUInt

  val MU_OPT_ALIGNCENTER: CUnsignedInt = 1.toUInt
  val MU_OPT_ALIGNRIGHT: CUnsignedInt  = 2.toUInt
  val MU_OPT_NOINTERACT: CUnsignedInt  = 4.toUInt
  val MU_OPT_NOFRAME: CUnsignedInt     = 8.toUInt
  val MU_OPT_NORESIZE: CUnsignedInt    = 16.toUInt
  val MU_OPT_NOSCROLL: CUnsignedInt    = 32.toUInt
  val MU_OPT_NOCLOSE: CUnsignedInt     = 64.toUInt
  val MU_OPT_NOTITLE: CUnsignedInt     = 128.toUInt
  val MU_OPT_HOLDFOCUS: CUnsignedInt   = 256.toUInt
  val MU_OPT_AUTOSIZE: CUnsignedInt    = 512.toUInt
  val MU_OPT_POPUP: CUnsignedInt       = 1024.toUInt
  val MU_OPT_CLOSED: CUnsignedInt      = 2048.toUInt
  val MU_OPT_EXPANDED: CUnsignedInt    = 4096.toUInt

  val MU_COLOR_TEXT: CUnsignedInt        = 0.toUInt
  val MU_COLOR_BORDER: CUnsignedInt      = 1.toUInt
  val MU_COLOR_WINDOWBG: CUnsignedInt    = 2.toUInt
  val MU_COLOR_TITLEBG: CUnsignedInt     = 3.toUInt
  val MU_COLOR_TITLETEXT: CUnsignedInt   = 4.toUInt
  val MU_COLOR_PANELBG: CUnsignedInt     = 5.toUInt
  val MU_COLOR_BUTTON: CUnsignedInt      = 6.toUInt
  val MU_COLOR_BUTTONHOVER: CUnsignedInt = 7.toUInt
  val MU_COLOR_BUTTONFOCUS: CUnsignedInt = 8.toUInt
  val MU_COLOR_BASE: CUnsignedInt        = 9.toUInt
  val MU_COLOR_BASEHOVER: CUnsignedInt   = 10.toUInt
  val MU_COLOR_BASEFOCUS: CUnsignedInt   = 11.toUInt
  val MU_COLOR_SCROLLBASE: CUnsignedInt  = 12.toUInt
  val MU_COLOR_SCROLLTHUMB: CUnsignedInt = 13.toUInt
  val MU_COLOR_MAX: CUnsignedInt         = 14.toUInt

object types:
  export _root_.microuilib.structs.*
  export _root_.microuilib.aliases.*
  export _root_.microuilib.unions.*
