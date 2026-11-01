configuration test1_2();
    data a = 0;
    data b = 1;
    data c = 0;

    repeat (6) period=1 c = a + b;
    repeat (6) period=1 a = b;
    repeat (6) period=1 b = c;
    repeat (6) period=1 display({a, b, c});
endconfiguration
