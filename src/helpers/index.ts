import { CartItemData, LoginRequest, OrderStatus, ReviewData, SignupRequest } from "@/types/models";

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

// format iso date to more readable one
export const formatDate = (isoDate: Date, options = {}) => {
  if (!isoDate) {
    throw new Error("Invalid ISO date string");
  }

  const defaultOptions: Intl.DateTimeFormatOptions = {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    timeZoneName: 'short',
  };

  const formatOptions = { ...defaultOptions, ...options };

  const date = new Date(isoDate);

  return new Intl.DateTimeFormat('en-US', formatOptions).format(date);
}

export const isOutOfStock = (quantity: number) => {
  return quantity === 0;
};

export const computeAverageRating = (reviews: ReviewData[]) => {
  if (reviews.length === 0) {
    return 0;
  }

  return reviews.reduce((acc, el) => acc + el.rating, 0) / reviews.length;
};

export const validateReviewForm = (rating: number, content: string) => {
  if (rating === 0) {
    alert('Please select a rating');
    return false;
  }

  if (content.trim().length < 3) {
    alert('Review must be at least 3 characters long');
    return false;
  }

  return true;
};

export const computeSubtotal = (items: CartItemData[]) => {
  return items.reduce((acc, el) => el.price + acc, 0);
};

export const getStatusColor = (status: OrderStatus) => {
  switch (status) {
    case OrderStatus.PENDING:
      return "bg-yellow-500";
    case OrderStatus.DELIVERING:
      return "bg-blue-500";
    case OrderStatus.COMPLETED:
      return "bg-green-500";
    case OrderStatus.CANCELLED:
      return "bg-red-500";
  }
};