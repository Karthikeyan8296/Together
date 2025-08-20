import Header from "@/components/Header";
import InputField from "@/components/InputField";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { IMAGES, ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import { loginUser } from "@/redux/slices/authSlice";
import { AppDispatch } from "@/redux/store";
import { toastConfig } from "@/util/toastConfig";
import { ChevronLeft } from "lucide-react-native";
import React, { useRef, useState } from "react";
import { ActivityIndicator, Image, ScrollView, View } from "react-native";
import Toast from "react-native-toast-message";
import { useDispatch } from "react-redux";

const Login = ({ navigation }: any) => {
  const emailRef = useRef("");
  const passwordRef = useRef("");

  const [loading, setLoading] = useState(false);

  // States for email input
  const [inputState, setInputState] = useState<"default" | "error" | "success">(
    "default"
  );
  const [helperText, setHelperText] = useState("");

  // States for password
  const [PasswordState, setPasswordState] = useState<
    "default" | "error" | "success"
  >("default");
  const [passwordHelperText, setPasswordHelperText] = useState("");

  // Validations
  const validateEmail = (email: string) => {
    const isValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    return isValid;
  };
  const validatePassword = (password: string): boolean => {
    const regex = /^(?=.*[A-Za-z])(?=.*[\d\W]).{8,}$/;
    return regex.test(password);
  };

  //redux
  const dispatch = useDispatch<AppDispatch>();

  const handleLogin = async () => {
    if (loading) return;

    const email = emailRef.current.trim();
    const password = passwordRef.current.trim();

    //Email
    if (email === "" || !validateEmail(email)) {
      setInputState("error");
      setHelperText("That doesn't look right");
      return;
    } else {
      setInputState("default");
      setHelperText("");
    }

    //Password
    if (password.length < 8 || !validatePassword(password)) {
      setPasswordState("error");
      setPasswordHelperText("That doesn't look right");
    }
    try {
      setLoading(true);

      //login api hit
      await dispatch(loginUser({ email, password })).unwrap();

      navigation.replace(ROUTES.APP_NAVIAGATION_STACK);
    } catch (err: any) {
      console.log("Login error: ", err);
      Toast.show({
        topOffset: 52,
        type: "error",
        text1: "Login Failed",
        text2: err?.toString() ?? "Something went wrong",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <ScreenWrapper>
      <Header
        leftIcon={
          <ChevronLeft strokeWidth={2.5} onPress={() => navigation.goBack()} />
        }
      />
      {/* Body */}
      <ScrollView className="flex-1 px-8">
        <View className="items-center mt-8">
          <Image
            source={IMAGES.BrandLogoBlack}
            style={{ height: 100, width: 170 }}
            resizeMode="contain"
          />
        </View>
        {/* Form */}
        <View className="mt-2">
          <View>
            <Typo font="Inter_semiBold" size={16}>
              Email
            </Typo>
            <InputField
              onChangeText={(value) => (emailRef.current = value)}
              placeholder="example@email.com"
              state={inputState}
              helperText={helperText}
            />
          </View>
          <Typo className="mt-6" font="Inter_semiBold" size={16}>
            Password
          </Typo>
          <InputField
            onChangeText={(value) => (passwordRef.current = value)}
            placeholder="Enter password"
            secure
            state={PasswordState}
            helperText={passwordHelperText}
          />
        </View>
        {/* Action Buttons */}
        <View className="mt-6 flex-row justify-between gap-x-4">
          <PrimaryButton width={74} onPress={handleLogin} disabled={loading}>
            {loading ? (
              <ActivityIndicator size="small" color="#fff" />
            ) : (
              "Log in"
            )}
          </PrimaryButton>
          <PrimaryButton
            width={164}
            bg_color={colors.white}
            text_color={colors.text_primary}
            onPress={() => {}}
          >
            Forgot password?
          </PrimaryButton>
        </View>
      </ScrollView>
      <Toast config={toastConfig} />
    </ScreenWrapper>
  );
};

export default Login;
