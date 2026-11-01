configuration ternary_demo;

    data [3:0] a   = 10;
    data [3:0] b   = 20;
    data [3:0] max = 0;
    data clk       = 0;

    repeat (FOREVER) period=2 clk = ~clk;
    repeat (FOREVER) period=4 a   = a + 2;
    repeat (FOREVER) period=4 b   = b + 1;
    repeat (FOREVER) period=4 max = (a > b) ? a : b;
    repeat (FOREVER) period=4 display({"clk=", clk, ", a=", a, ", b=", b, ", max=", max});

endconfiguration
