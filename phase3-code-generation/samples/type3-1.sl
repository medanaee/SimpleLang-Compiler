componenttype AddMul(
    port in data a,
    port in data b,
    port out data r
);
    repeat (2) period=1 set r = (a + b) * 2;
endcomponenttype

componenttype Combine(
    port in data x,
    port in data y,
    port out data z
);
    AddMul u1(.a(x), .b(y), .r(z));
endcomponenttype

configuration test3();
    data p = 3;
    data q = 4;
    data outt = 0;

    Combine c1(.x(p), .y(q), .z(outt));

    repeat (2) period=1 display({outt});
endconfiguration