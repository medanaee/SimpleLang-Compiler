componenttype A();
  data a = true; 
  data b = 3;
  data c = 3.9;

  data res1 = a + b;
  data res2 = b + c;
  data res3 = b % 2;
  data res4 = c % b;
  data res5 = ++b;
  data res6 = ++c;
  data res7 = a == false;
  data res8 = a < b;
  data res9 = a && (b == 3);
  data res10 = b && a;
  data res11 = !a;
  data res12 = !b;
endcomponenttype

configuration Scenario;
endconfiguration
