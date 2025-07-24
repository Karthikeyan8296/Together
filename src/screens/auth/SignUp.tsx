import Header from "@/components/Header";
import InputField from "@/components/InputField";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import { LinearGradient } from "expo-linear-gradient";
import { ChevronLeft } from "lucide-react-native";
import React, { useEffect, useRef, useState } from "react";
import { ActivityIndicator, ScrollView, StyleSheet, View } from "react-native";
import Animated, { FadeIn } from "react-native-reanimated";

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

      navigation.navigate(ROUTES.ONBOARDINGSTACK);
    } catch (err: any) {
      console.log("Error during signup:", err);
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
      <LinearGradient
        colors={["#f8fafc", "#e2e8f0", "#f1f5f9"]}
        style={styles.gradientBg}
      >
        <Header
          leftIcon={
            <ChevronLeft
              strokeWidth={2.5}
              onPress={() => navigation.goBack()}
            />
          }
        />
        <ScrollView contentContainerStyle={styles.scrollContainer}>
          <Animated.View
            entering={FadeIn.duration(700)}
            style={styles.headerSection}
          >
            <Typo font="Fustat_ExtraBold_800" size={20} style={styles.title}>
              Sign up
            </Typo>
            <Typo font="Fustat_Bold_700" size={14} style={styles.subtitle}>
              Create your free account and start exploring events around you
            </Typo>
          </Animated.View>
          <Animated.View
            entering={FadeIn.duration(900)}
            style={styles.formContainer}
          >
            <View style={styles.inputGroup}>
              <Typo font="Fustat_Bold_700" size={16} style={styles.label}>
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
            {showCodeInput && !showPasswordInput && (
              <Animated.View
                entering={FadeIn.duration(700)}
                style={styles.inputGroup}
              >
                <Typo font="Fustat_Bold_700" size={16} style={styles.label}>
                  Sign up code
                </Typo>
                <InputField
                  onChangeText={(value) => (signUpCodeRef.current = value)}
                  placeholder="✱✱✱✱✱✱"
                  state={codeInputState}
                  helperText={codeHelperText}
                  keyboardType="number-pad"
                />
              </Animated.View>
            )}
            {showPasswordInput && (
              <Animated.View
                entering={FadeIn.duration(700)}
                style={styles.inputGroup}
              >
                <Typo font="Fustat_Bold_700" size={16} style={styles.label}>
                  Set Password
                </Typo>
                <InputField
                  onChangeText={(value) => (passwordRef.current = value)}
                  placeholder="New password"
                  state={PasswordState}
                  secure
                  helperText={passwordHelperText}
                />
              </Animated.View>
            )}
          </Animated.View>
          <Animated.View
            entering={FadeIn.duration(1100)}
            style={styles.buttonRow}
          >
            <PrimaryButton
              width={showPasswordInput ? 150 : 94}
              style={styles.primaryBtn}
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
              width={130}
              bg_color={colors.white}
              text_color={colors.text_primary}
              style={styles.secondaryBtn}
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
          </Animated.View>
        </ScrollView>
      </LinearGradient>
    </ScreenWrapper>
  );
};

const styles = StyleSheet.create({
  gradientBg: {
    flex: 1,
  },
  scrollContainer: {
    flexGrow: 1,
    justifyContent: "center",
    alignItems: "center",
    paddingHorizontal: 24,
    paddingVertical: 32,
  },
  headerSection: {
    width: "100%",
    marginBottom: 12,
    alignItems: "flex-start",
  },
  title: {
    marginBottom: 4,
  },
  subtitle: {
    marginBottom: 0,
    color: colors.text_secondary,
  },
  formContainer: {
    width: "100%",
    backgroundColor: "#fff",
    borderRadius: 24,
    padding: 28,
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowRadius: 16,
    elevation: 6,
    marginBottom: 28,
    borderWidth: 1,
    borderColor: "#e2e8f0",
  },
  inputGroup: {
    marginBottom: 22,
    width: "100%",
  },
  label: {
    marginBottom: 10,
    color: "#334155",
  },
  buttonRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    width: "100%",
    gap: 18,
    marginBottom: 36,
  },
  primaryBtn: {
    borderRadius: 12,
    backgroundColor: "#334155",
    elevation: 2,
  },
  secondaryBtn: {
    borderRadius: 12,
    borderWidth: 1,
    borderColor: "#e2e8f0",
    backgroundColor: "#fff",
  },
});

export default SignUp;
