componenttype Add2(
  port in  data A,
  port in  data B,
  port out data Y
);
  set Y = A + B;
endcomponenttype

configuration ArgumentMismatch_1;
  data a = 1;
  data b = 2;
  data y = 0;

  Add2 u0(.A(a), .Y(y));
  Add2 u1(.A(a), .B(b), .C(a));
endconfiguration
