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
    | "Inter_black"
    | "Inter_extraBold"
    | "Inter_bold"
    | "Inter_semiBold"
    | "Inter_medium"
    | "Inter_regular";
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

export type ImageUploadProps = {
  file?: any;
  onSelect: (file: any) => void;
  onClear: () => void;
  containerStyle?: ViewStyle;
  aspectRatio?: [number, number];
  allowEditing?: boolean;
  imageStyle?: ViewStyle;
  placeholder?: string;
  borderRadius?: any;
};