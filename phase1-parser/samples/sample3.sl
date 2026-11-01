configuration fifo_demo;

    parameter DEPTH = 8;
    parameter WIDTH = 4;

    data clk = 0;
    data rst = 0;

    data wr_en = 0;
    data rd_en = 0;
    data [WIDTH-1:0] din  = 0;
    data [WIDTH-1:0] dout = 0;

    data [WIDTH-1:0] mem[DEPTH];
    data wr_ptr = 0;
    data rd_ptr = 0;
    data count  = 0;

    repeat (FOREVER) period=4 clk = ~clk;

    repeat (1) period=2 rst = 1'b1;
    repeat (1) period=6 rst = 1'b0;

    repeat (FOREVER) period=8  wr_en = ~wr_en;
    repeat (FOREVER) period=12 rd_en = ~rd_en;

    repeat (FOREVER) period=4 din = din + 1;

    // Write logic - guarded by (!rst && wr_en && (count < DEPTH))
    repeat (15) period=4 if (!rst && wr_en && (count < DEPTH))
        mem[wr_ptr] = din;

    repeat (15) period=4 if (!rst && wr_en && (count < DEPTH))
        wr_ptr = (wr_ptr + 1) % DEPTH;

    repeat (15) period=4 if (!rst && wr_en && (count < DEPTH))
        count = count + 1;

    repeat (15) period=4 if (!rst && wr_en && (count < DEPTH))
        display({"WRITE: din=", din, ", wr_ptr=", wr_ptr, ", count=", count});

    // Read logic - guarded by (!rst && rd_en && (count > 0))
    repeat (15) period=4 if (!rst && rd_en && (count > 0))
        dout = mem[rd_ptr];

    repeat (15) period=4 if (!rst && rd_en && (count > 0))
        rd_ptr = (rd_ptr + 1) % DEPTH;

    repeat (15) period=4 if (!rst && rd_en && (count > 0))
        count = count - 1;

    repeat (15) period=4 if (!rst && rd_en && (count > 0))
        display({"READ: dout=", dout, ", rd_ptr=", rd_ptr, ", count=", count});

endconfiguration