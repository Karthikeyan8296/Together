import React from "react";
import { View } from "react-native";
import { headerProps } from "types";
import Typo from "./Typo";

const Header = ({ style, leftIcon, rightIcon, title = "" }: headerProps) => {
  return (
    <View style={style} className="w-full items-center flex-row h-20 py-6 px-8">
      {leftIcon && <View className="self-center">{leftIcon}</View>}
      {title && (
        <Typo
          size={16}
          fontWeight={"600"}
          style={{ textAlign: "center", width: leftIcon ? "86%" : "20%" }}
        >
          {title}
        </Typo>
      )}
      {rightIcon && <View className="self-center">{rightIcon}</View>}
    </View>
  );
};

export default Header;
