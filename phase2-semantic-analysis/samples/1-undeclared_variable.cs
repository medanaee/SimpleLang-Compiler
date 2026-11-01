configuration UseUndeclaredVar;
  data x = 0;
  set x = y;   // y is NOT declared anywhere
endconfiguration
