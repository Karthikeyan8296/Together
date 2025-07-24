import Header from "@/components/Header";
import InputField from "@/components/InputField";
import PrimaryButton from "@/components/PrimaryButton";
import ScreenWrapper from "@/components/ScreenWrapper";
import Typo from "@/components/Typo";
import { IMAGES } from "@/constants";
import { colors } from "@/constants/colors";
import { ChevronLeft } from "lucide-react-native";
import React, { useRef, useState } from "react";
import {
  ActivityIndicator,
  Image,
  ScrollView,
  StyleSheet,
  View,
} from "react-native";
import { LinearGradient } from 'expo-linear-gradient';
import Animated, { FadeIn } from "react-native-reanimated";

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
    } else {
      setPasswordState("success");
      setPasswordHelperText("That looks right");
    }
    try {
      setLoading(true);
    } catch (err: any) {
      console.log("Login error:");
      setHelperText("Incorrect credentials. Please try again.");
      setInputState("error");
      setPasswordState("error");
    } finally {
      setLoading(false);
    }
  };

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
            style={styles.logoContainer}
          >
            <Image
              source={IMAGES.BrandLogoBlack}
              style={styles.logo}
              resizeMode="contain"
            />
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
                helperText={helperText}
              />
            </View>
            <View style={styles.inputGroup}>
              <Typo font="Fustat_Bold_700" size={16} style={styles.label}>
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
          </Animated.View>
          <Animated.View
            entering={FadeIn.duration(1100)}
            style={styles.buttonRow}
          >
            <PrimaryButton
              width={120}
              style={styles.primaryBtn}
              onPress={handleLogin}
              disabled={loading}
            >
              {loading ? (
                <ActivityIndicator size="small" color="#fff" />
              ) : (
                "Log in"
              )}
            </PrimaryButton>
            <PrimaryButton
              width={160}
              bg_color={colors.white}
              text_color={colors.text_primary}
              style={styles.secondaryBtn}
              onPress={() => navigation.navigate("")}
            >
              Forgot password?
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
  logoContainer: {
    alignItems: "center",
    marginTop: 24,
    marginBottom: 16,
  },
  logo: {
    height: 90,
    width: 160,
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

export default Login;
