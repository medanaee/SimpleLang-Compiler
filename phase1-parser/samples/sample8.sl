`timescale 1ns/1ps

componenttype BUFG(port in data I, port out data O);
  set O = I;
endcomponenttype

componenttype Top_TimerBuf(port in data timer_in, port out data timer_out);
  BUFG u(.I(timer_in), .O(timer_out));
endcomponenttype

configuration Scenario;
  data timer0, timer1;
  data timer0_buf, timer1_buf;

  Top_TimerBuf u0(.timer_in(timer0), .timer_out(timer0_buf));
  Top_TimerBuf u1(.timer_in(timer1), .timer_out(timer1_buf));

  -- timer0 = 1'b0;  timer1 = 1'b0;

  repeat (FOREVER) period=5  timer0 = ~timer0;
  repeat (FOREVER) period=8  timer1 = ~timer1;

  repeat (FOREVER) period=5  display(timer0_buf);
  repeat (FOREVER) period=8  display(timer1_buf);
endconfiguration
