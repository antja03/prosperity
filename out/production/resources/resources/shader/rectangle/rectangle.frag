#version 110

uniform float radius;
uniform vec4 innerRect;

varying vec2 pos;

void main() {
    vec2 tl = innerRect.xy - pos;
    vec2 br = pos - innerRect.zw;
    vec2 dis = max(br, tl);

    float v = length(max(vec2(0.0), dis)) - radius;
    float a = 1.0 - smoothstep(0.0, 1.0, v);
    gl_FragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);
}