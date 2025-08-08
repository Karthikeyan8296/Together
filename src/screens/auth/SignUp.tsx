import Header from "@/components/Header";
import InputField from "@/components/InputField";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import { sendOtp, signUpUser, verifyEmail } from "@/redux/slices/authSlice";
import { AppDispatch, RootState } from "@/redux/store";
import { toastConfig } from "@/util/toastConfig";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { ChevronLeft } from "lucide-react-native";
import React, { useEffect, useRef, useState } from "react";
import { ActivityIndicator, View } from "react-native";
import Toast from "react-native-toast-message";
import { useDispatch, useSelector } from "react-redux";

const SignUp = ({ navigation }: any) => {
  const [loading, setLoading] = useState(false);

  const emailRef = useRef("");
  const signUpCodeRef = useRef("");
  const passwordRef = useRef("");

  const [edit, setEdit] = useState(true);

  // States for email input
  const [inputState, setInputState] = useState<"default" | "error" | "success">(
    "default"
  );
  const [helperText, setHelperText] = useState("");
  const [showCodeInput, setShowCodeInput] = useState(false);

  // States for sign up code
  const [codeInputState, setCodeInputState] = useState<
    "default" | "error" | "success"
  >("default");
  const [codeHelperText, setCodeHelperText] = useState("");
  const [showPasswordInput, setPasswordInput] = useState(false);

  // States for password
  const [PasswordState, setPasswordState] = useState<
    "default" | "error" | "success"
  >("default");
  const [passwordHelperText, setPasswordHelperText] = useState(
    "Minimum of 8 characters, include one letter and one number or symbol"
  );

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
  const { signupToken } = useSelector((state: RootState) => state.auth);

  const handleContinue = async () => {
    if (loading) return;
    setLoading(true);

    const code = signUpCodeRef.current.trim();
    const email = emailRef.current.trim();
    const password = passwordRef.current.trim();

    try {
      // Step 1: Send OTP
      if (!showCodeInput) {
        if (!validateEmail(email)) {
          setInputState("error");
          setHelperText("That doesn't look right");
          setLoading(false);
          return;
        }

        //sendOtp API hit
        const result = await dispatch(sendOtp(email)).unwrap();

        setInputState("success");
        setHelperText(
          "We sent a temporary sign-up code. Check your inbox and enter it below."
        );
        setShowCodeInput(true);
        setLoading(false);
        return;
      }

      // Step 2: Verify OTP
      if (!showPasswordInput) {
        if (code === "") {
          setCodeInputState("error");
          setCodeHelperText("Enter the code");
          setLoading(false);
          return;
        }

        //verifyOTP API hit
        const result = await dispatch(
          verifyEmail({ email, otp: code })
        ).unwrap();

        setCodeInputState("success");
        setCodeHelperText("That looks right");
        setPasswordInput(true);
        setLoading(false);
        return;
      }

      // Step 3: Create Account
      if (!validatePassword(password)) {
        setPasswordState("error");
        setPasswordHelperText("That doesn't look right");
        setLoading(false);
        return;
      }

      //signup API hit
      const result = await dispatch(
        signUpUser({ signupToken: signupToken!, password })
      ).unwrap();

      const { user, token } = result;

      //persist signup setup
      await AsyncStorage.setItem("token", token);
      await AsyncStorage.setItem("user", JSON.stringify(user));

      navigation.replace(ROUTES.APP_NAVIAGATION_STACK);
    } catch (err: any) {
      console.log("Error during signup:", err);
      Toast.show({
        topOffset: 52,
        type: "error",
        text1: "Signup Failed",
        text2: err?.toString() ?? "Something went wrong",
      });
    } finally {
      setLoading(false);
    }
  };

  const handleLoginInstead = () => {
    navigation.navigate(ROUTES.LOGIN);
  };

  const handleResendCode = () => {
    console.log("Resending code to", emailRef.current);
  };

  useEffect(() => {
    if (showPasswordInput) {
      setHelperText("");
      setEdit(false);
    }
  }, [showPasswordInput]);

  return (
    <ScreenWrapper>
      <Header
        leftIcon={
          <ChevronLeft strokeWidth={2.5} onPress={() => navigation.goBack()} />
        }
      />

      <View className="flex-1 px-8">
        <View className="flex-row justify-between">
          <View>
            <Typo font="Inter_extraBold" size={20}>
              Sign up
            </Typo>
            <Typo font="Inter_medium" size={14}>
              Create your free account and start exploring events around you
            </Typo>
          </View>
        </View>

        {/* form  */}
        <View className="mt-6">
          {/* Email Field */}
          <View>
            <Typo font="Inter_semiBold" size={16}>
              Email
            </Typo>
            <InputField
              onChangeText={(value) => (emailRef.current = value)}
              placeholder="example@email.com"
              state={inputState}
              editable={edit}
              helperText={helperText}
            />
          </View>
          {/* Signup code Field */}
          {showCodeInput && !showPasswordInput && (
            <View className="mt-6">
              <Typo font="Inter_semiBold" size={16}>
                Sign up code
              </Typo>
              <InputField
                onChangeText={(value) => (signUpCodeRef.current = value)}
                placeholder="✱✱✱✱✱✱"
                state={codeInputState}
                helperText={codeHelperText}
                keyboardType="number-pad"
              />
            </View>
          )}
          {/* Password Field */}
          {showPasswordInput && (
            <>
              <View className="mt-6">
                <Typo font="Inter_semiBold" size={16}>
                  Set Password
                </Typo>
                <InputField
                  onChangeText={(value) => (passwordRef.current = value)}
                  placeholder="New password"
                  state={PasswordState}
                  secure
                  helperText={passwordHelperText}
                />
              </View>
            </>
          )}
        </View>
        {/* Action Buttons */}
        <View className="mt-6 flex-row justify-between gap-x-4">
          <PrimaryButton
            width={showPasswordInput ? 150 : 94}
            onPress={handleContinue}
            disabled={loading}
          >
            {loading ? (
              <ActivityIndicator size="small" color="#fff" />
            ) : showPasswordInput ? (
              "Create account"
            ) : (
              "Continue"
            )}
          </PrimaryButton>

          <PrimaryButton
            width={134}
            bg_color="white"
            text_color={colors.text_primary}
            onPress={() => {
              if (!showCodeInput && !showPasswordInput) {
                handleLoginInstead();
              } else if (showCodeInput && !showPasswordInput) {
                handleResendCode();
              }
            }}
          >
            {!showCodeInput && !showPasswordInput
              ? "Log in instead"
              : showCodeInput && !showPasswordInput
                ? "Resend code"
                : ""}
          </PrimaryButton>
        </View>
      </View>
      <Toast config={toastConfig} />
    </ScreenWrapper>
  );
};

export default SignUp;
