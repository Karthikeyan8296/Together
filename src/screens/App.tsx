import RootNavigation from "@/navigation/RootNavigation";
import { NavigationContainer } from "@react-navigation/native";
import { defaultConfig } from "@tamagui/config/v4";
import { PortalProvider } from "@tamagui/portal";
import React from "react";
import { createTamagui, TamaguiProvider } from "tamagui";
import "../../global.css";

// @ts-ignore
const config = createTamagui(defaultConfig);

const App = () => {
  return (
    <TamaguiProvider config={config}>
      <PortalProvider shouldAddRootHost>
        <NavigationContainer>
          <RootNavigation />
        </NavigationContainer>
      </PortalProvider>
    </TamaguiProvider>
  );
};

export default App;
