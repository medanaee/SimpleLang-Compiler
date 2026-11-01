configuration RepeatSameLHS;
  data x = 0;

  repeat (3) period=1 x = x + 1;
  repeat (2) period=1 x = x + 2;

endconfiguration
