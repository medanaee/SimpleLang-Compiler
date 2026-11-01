configuration test1_1();
    data x = 1;
    data y = 0;
    data z = 0;

    repeat (6) period=1 y = x + z;
    repeat (3) period=2 z = y - x;
    repeat (6) period=1 x = y;
    repeat (6) period=1 display({x, y, z});
endconfiguration