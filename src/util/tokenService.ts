import { createMMKV } from "react-native-mmkv";

export const storage = createMMKV();

const ACCESS_KEY = "accessToken";
const REFRESH_KEY = "refreshToken";
const USER_KEY = "user";

//get individual tokens
export const getAccessToken = () => storage.getString(ACCESS_KEY);
export const getRefreshToken = () => storage.getString(REFRESH_KEY);

//save the session values
export const saveSession = async (
  user: any,
  accessToken: string,
  refreshToken: string
) => {
  storage.set(USER_KEY, JSON.stringify(user));
  storage.set(ACCESS_KEY, accessToken);
  storage.set(REFRESH_KEY, refreshToken);
};

//load the session values
export const loadSession = async () => {
  const user = storage.getString(USER_KEY);
  const accessToken = storage.getString(ACCESS_KEY);
  const refreshToken = storage.getString(REFRESH_KEY);

  console.log("saved user", user);
  console.log("saved access Token", accessToken);
  console.log("saved refresh Token", refreshToken);

  return {
    user: user ? JSON.parse(user) : null,
    accessToken: accessToken || null,
    refreshToken: refreshToken || null,
  };
};

export const clearSession = async () => {
  storage.remove(USER_KEY);
  storage.remove(ACCESS_KEY);
  storage.remove(REFRESH_KEY);
};
