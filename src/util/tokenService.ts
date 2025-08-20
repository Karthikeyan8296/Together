import AsyncStorage from "@react-native-async-storage/async-storage";

const ACCESS_KEY = "accessToken";
const REFRESH_KEY = "refreshToken";
const USER_KEY = "user";

export const getAccessToken = () => AsyncStorage.getItem(ACCESS_KEY);
export const getRefreshToken = () => AsyncStorage.getItem(REFRESH_KEY);

export const saveSession = async (
  user: any,
  accessToken: string,
  refreshToken: string
) => {
  await AsyncStorage.multiSet([
    [USER_KEY, JSON.stringify(user)],
    [ACCESS_KEY, accessToken],
    [REFRESH_KEY, refreshToken],
  ]);
};

export const loadSession = async () => {
  const [[, user], [, accessToken], [, refreshToken]] =
    await AsyncStorage.multiGet([USER_KEY, ACCESS_KEY, REFRESH_KEY]);
  return {
    user: user ? JSON.parse(user) : null,
    accessToken: accessToken || null,
    refreshToken: refreshToken || null,
  };
};

export const clearSession = async () => {
  await AsyncStorage.multiRemove([USER_KEY, ACCESS_KEY, REFRESH_KEY]);
};
