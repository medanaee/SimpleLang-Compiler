configuration test2();
    data A = 4;
    data B = 6.0;
    data signed C = -3;
    data T1;
    data T2;
    data R;
    data condition_result;

    repeat (2) period=1 T1 = (A << 1) + (C & 1);
    repeat (2) period=1 condition_result = (A > C ? 1 : 0);
    repeat (2) period=1 T2 = (B / 2.0) + condition_result;
    repeat (2) period=1 R  = T1 + T2;
    repeat (2) period=1 display({A, B, C});
    repeat (2) period=1 display({T1});
    repeat (2) period=1 display({T2});
    repeat (2) period=1 display({R});
endconfiguration