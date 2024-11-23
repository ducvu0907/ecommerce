import { LoginFormData } from "@/pages/auth/Login";
import { SignupFormData } from "@/pages/auth/Signup";

export const validateLoginForm = (formData: LoginFormData): boolean => {
  if (!formData.username) {
    return false;
  }

  if (!formData.password) {
    return false;
  }
  return true;
};

export const validateSignupForm = (formData: SignupFormData): boolean => {
  if (!formData.username) {
    return false;
  }

  if (!formData.password) {
    return false;
  }

  if (!formData.firstName) {
    return false;
  }

  if (!formData.lastName) {
    return false;
  }

  if (!formData.phone) {
    return false;
  }

  if (!formData.role) {
    return false;
  }

  return true;
};