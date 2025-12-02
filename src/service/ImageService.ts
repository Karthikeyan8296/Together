//get the logo images service
export const getFilePath = (file: any) => {
  if (file && typeof file == "string") return file; // e.g. server image URL
  if (file && typeof file == "object") return file.uri; // local picked image

  return null;
};
