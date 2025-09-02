import { BottomTabBar, BottomTabBarProps } from "@react-navigation/bottom-tabs";
import { useEffect } from "react";
import { View } from "react-native";
import Animated, {
  useAnimatedStyle,
  useSharedValue,
  withTiming,
} from "react-native-reanimated";

function getActiveRouteName(route: any): string {
  // Recurse into nested navigators to find the deepest focused route name
  const state = route?.state ?? route?.params?.state;
  if (state && state.index != null) {
    const nested = state.routes[state.index];
    return getActiveRouteName(nested);
  }
  return route?.name ?? "";
}

type Props = BottomTabBarProps & {
  hiddenRoutes: string[];
};

export const AnimatedTabBar = (props: Props) => {
  const { state } = props;

  const currentRoute = state.routes[state.index];
  const activeRouteName = getActiveRouteName(currentRoute);
  const shouldHide = props.hiddenRoutes.includes(activeRouteName);

  // Animation values
  const translateY = useSharedValue(0);
  const opacity = useSharedValue(1);

  useEffect(() => {
    // Move the bar down and fade when hiding; bring it back when showing
    translateY.value = withTiming(shouldHide ? 84 : 0, { duration: 220 });
    opacity.value = withTiming(shouldHide ? 0 : 1, { duration: 180 });
  }, [shouldHide]);

  const animatedStyle = useAnimatedStyle(() => ({
    transform: [{ translateY: translateY.value }],
    opacity: opacity.value,
  }));

  return (
    <Animated.View
      // When hidden, ignore touches to the off-screen tab bar
      pointerEvents={shouldHide ? "none" : "auto"}
      style={[
        { position: "absolute", left: 0, right: 0, bottom: 0 },
        animatedStyle,
      ]}
    >
      <View /* shadow removed per your style */>
        <BottomTabBar {...props} />
      </View>
    </Animated.View>
  );
};
