import { colors } from "@/constants/colors";
import React from "react";
import { Text, TextStyle } from "react-native";
import { TypoProps } from "types";

const Typo = ({
  size,
  color = colors.text_primary,
  font = "Inter_medium",
  fontWeight = "400",
  children,
  style,
  className,
  textProps = {}, //this will have all the text props
}: TypoProps) => {
  const textStyle: TextStyle = {
    fontSize: size,
    color,
    fontWeight,
    fontFamily: font,
  };

  return (
    <Text className={className} style={[textStyle, style]} {...textProps}>
      {children}
    </Text>
  );
};

export default Typo;
