`timescale 1ns/1ps

componenttype FDRE #(parameter INIT = 1'b0)(
  port out data Q, port in data C, port in data CE, port in data R, port in data D
);
endcomponenttype

componenttype BUFG(port in data I, port out data O);
  set O = I;
endcomponenttype

componenttype CounterN #(parameter int N = 8)(
  port in  data         timer,
  port in  data         rst,
  port out data [N-1:0] q
);
  data [N-1:0] d, qq;
  set d = qq + 1'b1;

  index i;
  expand
    for (i = 0; i < N; i++) do : G
      FDRE #(.INIT(1'b0)) ff (.Q(qq[i]), .C(timer), .CE(1'b1), .R(rst), .D(d[i]));
    enddo
  endexpand

  set q = qq;
endcomponenttype

configuration Top_Counters(
  port in  data        timer0_in,
  port in  data        timer1_in,
  port in  data        rst,
  port out data [7:0]  q0,
  port out data [7:0]  q1
);
  data c0, c1;
  BUFG b0(.I(timer0_in), .O(c0));
  BUFG b1(.I(timer1_in), .O(c1));
  CounterN #(.N(8)) u0 (.timer(c0), .rst(rst), .q(q0));
  CounterN #(.N(4)) u1 (.timer(c1), .rst(rst), .q(q1));
endconfiguration

configuration Scenario;
  data timer0, timer1, rst;
  data [7:0] q0, q1;
  data timer0;

  Top_Counters_1 dut(.timer0_in(timerx), .timer1_in(timer1), .rst(rst), .q0(q0), .q1(q1));

  Top_Counters dut(.timer0_in(timerx), .timer1_in(timer1), .rst(rst), .q0(q0), .q1(q1));

  -- timer0 = 1'b0; timer1 = 1'b0; rst = 1'b0;

  repeat (FOREVER) period=5  timer0 = ~timer0;
  repeat (FOREVER) period=8  timer1 = ~timer1;

  repeat (1)  period=5  rst = 1'b1;
  repeat (1)  period=15 rst = 1'b0;

  repeat (10) period=5  display(q0);
  repeat (10) period=8  display(q1);
endconfiguration
