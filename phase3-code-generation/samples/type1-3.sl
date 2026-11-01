configuration test1_3();
    data acc = 0;
    data last = 1;

    repeat (8) period=1 acc = acc + last;
    repeat (4) period=2 last = acc;
    repeat (8) period=1 display({acc, last});

endconfiguration