import { store } from "@/redux/store";
import { NavigationContainer } from "@react-navigation/native";
import {
  focusManager,
  QueryClient,
  QueryClientProvider,
} from "@tanstack/react-query";
import { useFonts } from "expo-font";
import React from "react";
import { AppState } from "react-native";
import Toast from "react-native-toast-message";
import { Provider } from "react-redux";
import "../../global.css";
import RestoredApp from "./RestoredApp";

const App = () => {
  const [fontLoaded] = useFonts({
    Inter_thin: require("@/assets/fonts/Inter_18pt-Thin.ttf"),
    Inter_extraLight: require("@/assets/fonts/Inter_18pt-ExtraLight.ttf"),
    Inter_light: require("@/assets/fonts/Inter_18pt-Light.ttf"),
    Inter_regular: require("@/assets/fonts/Inter_18pt-Regular.ttf"),
    Inter_medium: require("@/assets/fonts/Inter_18pt-Medium.ttf"),
    Inter_semiBold: require("@/assets/fonts/Inter_18pt-SemiBold.ttf"),
    Inter_bold: require("@/assets/fonts/Inter_18pt-Bold.ttf"),
    Inter_extraBold: require("@/assets/fonts/Inter_18pt-ExtraBold.ttf"),
    Inter_black: require("@/assets/fonts/Inter_18pt-Black.ttf"),
  });
  if (!fontLoaded) return null;

  //TODO: need to take dev build again to make this work
  // onlineManager.setEventListener((setOnline) =>
  //   NetInfo.addEventListener((state) => setOnline(!!state.isConnected))
  // );

  focusManager.setEventListener((handleFocus) => {
    const subscribe = AppState.addEventListener("change", (status) =>
      handleFocus(status === "active")
    );
    return () => subscribe.remove();
  });

  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        //how long data is considered fresh
        staleTime: 10_000,
        //cache time before garbage collection
        gcTime: 5 * 60 * 1000,
        //automatic retry on failure
        retry: 1,
      },
    },
  });

  return (
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        <>
          <NavigationContainer>
            <RestoredApp />
          </NavigationContainer>
          <Toast />
        </>
      </Provider>
    </QueryClientProvider>
  );
};

export default App;
