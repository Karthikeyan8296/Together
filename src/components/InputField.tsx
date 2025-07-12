import { colors } from "@/constants/colors";
import { Eye, EyeOff } from "lucide-react-native";
import React, { useState } from "react";
import {
  StyleSheet,
  Text,
  TextInput,
  TextInputProps,
  TouchableOpacity,
  View,
} from "react-native";

type InputState = "default" | "error" | "success";

interface InputFieldProps extends TextInputProps {
  // value: string;
  onChangeText?: (text: string) => void;
  state?: InputState;
  helperText?: string;
  secure?: boolean;
  iconRight?: React.ReactNode;
}

const InputField: React.FC<InputFieldProps> = ({
  onChangeText,
  state = "default",
  helperText,
  secure = false,
  iconRight,
  ...rest
}) => {
  const [isFocused, setIsFocused] = useState(false);
  const [showPassword, setShowPassword] = useState(!secure);

  const getBorderColor = () => {
    if (state === "error") return "#FF8B8B";
    if (state === "success") return "#8ACFA2";
    if (isFocused) return "#7690E4";
    return "#EDF1FF";
  };

  const getHelperColor = () => {
    if (state === "error") return "#68718C";
    if (state === "success") return "#68718C";
    return "#68718C";
  };

  return (
    <View style={styles.container}>
      <View
        style={[
          styles.inputWrapper,
          { borderColor: getBorderColor(), borderWidth: 2 },
        ]}
      >
        <TextInput
          style={styles.input}
          onChangeText={onChangeText}
          placeholderTextColor="#8A95B4"
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          secureTextEntry={!showPassword && secure}
          {...rest}
        />
        {secure ? (
          <TouchableOpacity onPress={() => setShowPassword(!showPassword)}>
            {showPassword ? (
              <Eye size={20} strokeWidth={2} color={colors.text_primary} />
            ) : (
              <EyeOff size={20} strokeWidth={2} color={colors.text_primary} />
            )}
          </TouchableOpacity>
        ) : (
          iconRight
        )}
      </View>

      {helperText ? (
        <Text style={[styles.helperText, { color: getHelperColor() }]}>
          {helperText}
        </Text>
      ) : null}
    </View>
  );
};

export default InputField;

const styles = StyleSheet.create({
  container: {
    marginVertical: 0,
  },
  inputWrapper: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 12,
    paddingHorizontal: 12,
    width: "100%",
    height: 55,
    backgroundColor: "#F8FAFF",
  },
  input: {
    flex: 1,
    fontSize: 16,
    fontFamily: "font-Fustat_SemiBold_600",
    color: "#3A4155",
  },
  helperText: {
    marginTop: 4,
    fontSize: 14,
    fontWeight: "400",
    fontFamily: "",
  },
});
