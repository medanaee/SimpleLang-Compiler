configuration counter_bank;

    data clk   = 0, k = 9;
    data rst   = 0;
    data [3:0] data0 = 0;
    data [7:0] data1 = 0;

    repeat (FOREVER) period=1 clk = ~clk;

    repeat (1) period=4  rst = 1'b1;
    repeat (1) period=12 rst = 1'b0;

    repeat (17) period=5 if (clk == 1) data0 = data0 + 1;
    repeat (17) period=5 if (clk == 1) data1 = data0 + data1 + 1;
    repeat (17) period=5 if (clk == 1) display({"clk=", clk, ", data0=", data0, ", data1=", data1});

endconfiguration : k