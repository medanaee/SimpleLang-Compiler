configuration test1_4();
    data X = 0;
    repeat (6) period=1 X = (X & 1) == 0 ? X + 2 : X - 1;
    repeat (6) period=1 display({X});
endconfiguration