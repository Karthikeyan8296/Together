import React from "react";
import { ViewStyle } from "react-native";

export type ScreenWrapperProps = {
  style?: ViewStyle;
  children: React.ReactNode;
  className?: string;
  backgroundColor?: string;
};
