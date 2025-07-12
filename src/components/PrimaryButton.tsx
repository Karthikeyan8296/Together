import { colors } from "@/constants/colors";
import React from "react";
import { StyleSheet, TouchableOpacity } from "react-native";
import { PrimaryButtonProps } from "types";
import Typo from "./Typo";

const PrimaryButton = ({
  style,
  onPress,
  children,
  width = 125,
  bg_color = colors.primary,
  text_color = colors.white,
}: PrimaryButtonProps) => {
  return (
    <TouchableOpacity
      onPress={onPress}
      style={[styles.button, { width, backgroundColor: bg_color }, style]}
    >
      <Typo font="Fustat_Bold_700" size={16} color={text_color}>
        {children}
      </Typo>
    </TouchableOpacity>
  );
};

export default PrimaryButton;

const styles = StyleSheet.create({
  button: {
    height: 47,
    backgroundColor: colors.primary,
    borderRadius: 12,
    justifyContent: "center",
    alignItems: "center",
    paddingVertical: 12,
    paddingHorizontal: 12,
  },
});
