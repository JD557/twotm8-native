#include <string.h>
#include "microui.h"

int __sn_wrap_microuilib_mu_begin_window_ex(mu_Context * ctx, const char * title, mu_Rect *rect, int opt) {
 return mu_begin_window_ex(ctx, title, *rect, opt);
};


int __sn_wrap_microuilib_mu_check_clip(mu_Context * ctx, mu_Rect *r) {
 return mu_check_clip(ctx, *r);
};


void __sn_wrap_microuilib_mu_color(int r, int g, int b, int a, mu_Color *____return) {
  mu_Color ____ret = mu_color(r, g, b, a);
  memcpy(____return, &____ret, sizeof(mu_Color));
}


void __sn_wrap_microuilib_mu_draw_box(mu_Context * ctx, mu_Rect *rect, mu_Color *color) {
 mu_draw_box(ctx, *rect, *color);
};


void __sn_wrap_microuilib_mu_draw_control_frame(mu_Context * ctx, mu_Id id, mu_Rect *rect, int colorid, int opt) {
 mu_draw_control_frame(ctx, id, *rect, colorid, opt);
};


void __sn_wrap_microuilib_mu_draw_control_text(mu_Context * ctx, const char * str, mu_Rect *rect, int colorid, int opt) {
 mu_draw_control_text(ctx, str, *rect, colorid, opt);
};


void __sn_wrap_microuilib_mu_draw_icon(mu_Context * ctx, int id, mu_Rect *rect, mu_Color *color) {
 mu_draw_icon(ctx, id, *rect, *color);
};


void __sn_wrap_microuilib_mu_draw_rect(mu_Context * ctx, mu_Rect *rect, mu_Color *color) {
 mu_draw_rect(ctx, *rect, *color);
};


void __sn_wrap_microuilib_mu_draw_text(mu_Context * ctx, mu_Font font, const char * str, int len, mu_Vec2 *pos, mu_Color *color) {
 mu_draw_text(ctx, font, str, len, *pos, *color);
};


void __sn_wrap_microuilib_mu_get_clip_rect(mu_Context * ctx, mu_Rect *____return) {
  mu_Rect ____ret = mu_get_clip_rect(ctx);
  memcpy(____return, &____ret, sizeof(mu_Rect));
}


void __sn_wrap_microuilib_mu_layout_next(mu_Context * ctx, mu_Rect *____return) {
  mu_Rect ____ret = mu_layout_next(ctx);
  memcpy(____return, &____ret, sizeof(mu_Rect));
}


void __sn_wrap_microuilib_mu_layout_set_next(mu_Context * ctx, mu_Rect *r, int relative) {
 mu_layout_set_next(ctx, *r, relative);
};


int __sn_wrap_microuilib_mu_mouse_over(mu_Context * ctx, mu_Rect *rect) {
 return mu_mouse_over(ctx, *rect);
};


void __sn_wrap_microuilib_mu_push_clip_rect(mu_Context * ctx, mu_Rect *rect) {
 mu_push_clip_rect(ctx, *rect);
};


void __sn_wrap_microuilib_mu_rect(int x, int y, int w, int h, mu_Rect *____return) {
  mu_Rect ____ret = mu_rect(x, y, w, h);
  memcpy(____return, &____ret, sizeof(mu_Rect));
}


void __sn_wrap_microuilib_mu_set_clip(mu_Context * ctx, mu_Rect *rect) {
 mu_set_clip(ctx, *rect);
};


int __sn_wrap_microuilib_mu_textbox_raw(mu_Context * ctx, char * buf, int bufsz, mu_Id id, mu_Rect *r, int opt) {
 return mu_textbox_raw(ctx, buf, bufsz, id, *r, opt);
};


void __sn_wrap_microuilib_mu_update_control(mu_Context * ctx, mu_Id id, mu_Rect *rect, int opt) {
 mu_update_control(ctx, id, *rect, opt);
};


void __sn_wrap_microuilib_mu_vec2(int x, int y, mu_Vec2 *____return) {
  mu_Vec2 ____ret = mu_vec2(x, y);
  memcpy(____return, &____ret, sizeof(mu_Vec2));
}

