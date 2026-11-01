componenttype Top_TimerBuf(port in data arg1);
  data c = arg1 + 1;
endcomponenttype

configuration Scenario;
  data a, b, c;
  set b = false;
  Top_TimerBuf u0(.arg1(a));
  repeat (2) period=1 c = a == b; // error comes here because before this line we understood types of a and b
endconfiguration