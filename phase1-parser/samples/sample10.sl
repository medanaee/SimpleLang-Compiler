configuration fib_demo;

  data a = 1;
  data b = 1;
  data n = 1;

  -- step counter
  repeat (20) period=10 n++;

  -- Fibonacci update (no temps)
  repeat (20) period=10 a = a + b;     -- a := a + b
  repeat (20) period=10 b = a;     -- b := old a

  -- display
  repeat (20) period=10 display({n, a, b});
endconfiguration
