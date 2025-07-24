import PrimaryButton from "@/components/PrimaryButton";
import Typo from "@/components/Typo";
import { IMAGES, ROUTES } from "@/constants";
import { colors } from "@/constants/colors";
import React from "react";
import {
  Image,
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  View,
} from "react-native";
import Animated, { FadeIn } from "react-native-reanimated";

const Welcome = ({ navigation }: any) => {
  return (
    <ImageBackground source={IMAGES.Welcome_bg} style={styles.bg}>
      <View style={styles.container}>
        {/* Logo and brandings */}
        <Animated.View
          entering={FadeIn.duration(700)}
          style={styles.logoSection}
        >
          <Image
            source={IMAGES.BrandLogo}
            style={styles.logo}
            resizeMode="contain"
          />
          <Typo
            font="Fustat_Bold_700"
            size={18}
            color={colors.white}
            style={styles.tagline}
          >
            Connect. Celebrate. Repeat Together
          </Typo>
        </Animated.View>

        {/* Auth Buttons */}
        <Animated.View
          entering={FadeIn.duration(900)}
          style={styles.buttonSection}
        >
          <PrimaryButton
            width={268}
            onPress={() => navigation.navigate(ROUTES.SIGNUP)}
          >
            Sign up
          </PrimaryButton>
          <PrimaryButton
            width={268}
            bg_color={colors.background}
            text_color={colors.black}
            onPress={() => navigation.navigate(ROUTES.LOGIN)}
            style={styles.loginButton}
          >
            Log in
          </PrimaryButton>
        </Animated.View>

        {/* About Together */}
        <Animated.View
          entering={FadeIn.duration(1100)}
          style={styles.aboutSection}
        >
          <TouchableOpacity onPress={() => navigation.navigate("")}>
            <Typo
              color={colors.white}
              font="Fustat_Bold_700"
              size={16}
              style={styles.aboutText}
            >
              About Together
            </Typo>
          </TouchableOpacity>
        </Animated.View>
      </View>
    </ImageBackground>
  );
};

const styles = StyleSheet.create({
  bg: {
    flex: 1,
    justifyContent: "center",
  },
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    paddingHorizontal: 24,
    paddingVertical: 32,
  },
  logoSection: {
    alignItems: "center",
    marginBottom: 48,
  },
  logo: {
    width: 250,
    height: 80,
  },
  tagline: {
    paddingTop: 16,
    textAlign: "center",
  },
  buttonSection: {
    width: "100%",
    alignItems: "center",
    marginBottom: 32,
  },
  loginButton: {
    marginTop: 16,
  },
  aboutSection: {
    alignItems: "center",
    marginTop: 24,
  },
  aboutText: {
    marginTop: 8,
    textAlign: "center",
  },
});

export default Welcome;
