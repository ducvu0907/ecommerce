import { LoginRequest, SignupRequest } from "@/types/models";

export const validateLoginForm = (formData: LoginRequest): { isValid: boolean; errors: { [key: string]: string } } => {
  const errors: { [key: string]: string } = {};

  if (!formData.username) {
    errors.username = "Username is required.";
  }

  if (!formData.password) {
    errors.password = "Password is required.";
  }

  return {
    isValid: Object.keys(errors).length === 0,
    errors
  };
};

export const validateSignupForm = (formData: SignupRequest): { isValid: boolean; errors: { [key: string]: string } } => {
  const errors: { [key: string]: string } = {};

  if (!formData.username) {
    errors.username = "Username is required.";
  }

  if (!formData.password) {
    errors.password = "Password is required.";
  }

  if (!formData.firstName) {
    errors.firstName = "First name is required.";
  }

  if (!formData.lastName) {
    errors.lastName = "Last name is required.";
  }

  if (!formData.phone) {
    errors.phone = "Phone number is required.";
  }

  if (!formData.role) {
    errors.role = "Role is required.";
  }

  return {
    isValid: Object.keys(errors).length === 0,
    errors
  };
};
