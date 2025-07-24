import Typo from "@/components/Typo";
import { IMAGES } from "@/constants";
import { LinearGradient } from "expo-linear-gradient";
import React from "react";
import { Image, StyleSheet, View } from "react-native";
import Animated, { FadeIn } from "react-native-reanimated";

const OnBoadingWelcome = () => {
  return (
    <LinearGradient
      colors={["#f8fafc", "#e2e8f0", "#f1f5f9"]}
      style={styles.gradientBg}
    >
      <View style={styles.container}>
        <Animated.View
          entering={FadeIn.duration(700)}
          style={styles.logoContainer}
        >
          <View style={styles.logoWrapper}>
            <Image
              source={IMAGES.BrandLogo}
              style={styles.logo}
              resizeMode="contain"
              tintColor="#FFF"
            />
            {/* Gradient overlay */}
            <LinearGradient
              colors={["#6366f1", "#06b6d4"]}
              start={{ x: 0, y: 0 }}
              end={{ x: 1, y: 1 }}
              style={styles.logoGradient}
            />
          </View>
        </Animated.View>
        <Animated.View
          entering={FadeIn.duration(900)}
          style={styles.textContainer}
        >
          <Typo font="Fustat_ExtraBold_800" size={26} style={styles.title}>
            Welcome to Together!
          </Typo>
          <Typo font="Fustat_Bold_700" size={16} style={styles.subtitle}>
            Discover, connect, and celebrate with people around you.
          </Typo>
        </Animated.View>
        <Animated.View entering={FadeIn.duration(1200)} style={styles.footer}>
          <Typo font="Fustat_Bold_700" size={14} style={styles.footerText}>
            Swipe to get started →
          </Typo>
        </Animated.View>
      </View>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  gradientBg: {
    flex: 1,
  },
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    paddingHorizontal: 32,
    paddingVertical: 32,
  },
  logoContainer: {
    marginBottom: 24,
    alignItems: "center",
  },
  logoWrapper: {
    position: "relative",
    width: 140,
    height: 48,
    justifyContent: "center",
    alignItems: "center",
  },
  logo: {
    width: 140,
    height: 48,
    tintColor: "#fff",
    zIndex: 5,
  },
  logoGradient: {
    position: "absolute",
    width: 140,
    height: 48,
    borderRadius: 12,
    opacity: 0.8, // Adjust for effect
  },
  textContainer: {
    alignItems: "center",
    marginBottom: 18,
  },
  title: {
    textAlign: "center",
    color: "#334155",
    marginBottom: 8,
    letterSpacing: 0.5,
  },
  subtitle: {
    textAlign: "center",
    color: "#64748b",
    marginBottom: 0,
    letterSpacing: 0.2,
  },
  footer: {
    alignItems: "center",
    marginTop: 24,
  },
  footerText: {
    color: "#334155",
    opacity: 0.85,
    textAlign: "center",
    fontStyle: "italic",
  },
});

export default OnBoadingWelcome;
