componenttype Top_TimerBuf(port in data a, port out data b);
  set a = b;
endcomponenttype

configuration Scenario;
  data var_a = true; 
  data var_b = 3;
  Top_TimerBuf u0(.a(var_a), .b(var_b)); // error comes here because from type infrencing of Top_TimerBuf, we knew that a and b should have same type
endconfiguration