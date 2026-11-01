configuration test1_5();
    data X = 5;
    data Y = 2;
    data Z = 0;

    repeat (2) period=1 Z = (X > Y) ? ((X * 2) + (Y << 1)) : ((Y * 2) + X);
    repeat (2) period=1 display({Z});

endconfiguration
