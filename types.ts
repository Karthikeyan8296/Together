import React, { ReactNode } from "react";
import {
  TextProps,
  TextStyle,
  TouchableOpacityProps,
  ViewStyle,
} from "react-native";

export type ScreenWrapperProps = {
  style?: ViewStyle;
  children: React.ReactNode;
  className?: string;
  backgroundColor?: string;
};

export type headerProps = {
  style?: ViewStyle;
  title?: string;
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
};

export type TypoProps = {
  size?: number;
  color?: string;
  font?:
    | "Fustat_ExtraBold_800"
    | "Fustat_Bold_700"
    | "Fustat_SemiBold_600"
    | "Fustat_Medium_500"
    | "Fustat_Regular_400";
  fontWeight?: TextStyle["fontWeight"];
  children: React.ReactNode;
  style?: TextStyle;
  textProps?: TextProps;
  onPress?: () => void;
  className?: string;
};

export interface PrimaryButtonProps extends TouchableOpacityProps {
  width?: number;
  style?: ViewStyle;
  onPress?: () => void;
  children: React.ReactNode;
  bg_color?: string;
  text_color?: string;
}
