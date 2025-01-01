// router type
export type routerType = {
  title: string;
  path: string;
  element: JSX.Element;
};

export type ApiResponse<T> = {
  code: number;
  message: string;
  result?: T;
};

// requests
export type LoginRequest = {
  username: string;
  password: string;
};

export type SignupRequest = {
  username: string;
  password: string;
  phone: string;
  role: Role;
};

export type UpdateMeRequest = {
  username: string;
  fullName: string;
  phone: string;
};

export type CreateProductRequest = {
  title: string;
  description: string;
  imageUrl: string;
  sku: string;
  price: number;
  categoryId: string;
};

export type UpdateProductRequest = {
  title: string | null;
  description: string | null;
  imageUrl: string | null;
  sku: string | null;
  price: number | null;
  categoryId: string | null;
};

export type AddItemRequest = {
  productId: string;
  quantity: number;
};

export type UpdateItemRequest = {
  quantity: number;
};

export type CreateReviewRequest = {
  productId: string;
  rating: number;
  content: string;
};

export type UpdateReviewRequest = {
  rating: number;
  content: string;
};

export type CreateOrderItemRequest = {
  productId: string;
  price: number;
  quantity: number;
};

export type CreateOrderRequest = {
  address: string;
  instruction: string | null;
  discountId: string | null;
};

// TODO
export type CreatePaymentRequest = {

};

export type CreateInventoryRequest = {
  productId: string;
  location: string;
  stock: number;
};

export type UpdateInventoryRequest = {
  location: string;
  stock: number;
};

// responses
export type TokenData = {
  token: string;
};

export enum Role {
  BUYER = "BUYER",
  SELLER = "SELLER",
};

export type UserData = {
  id: string;
  username: string;
  fullName: string;
  phone: string;
  role: Role;
  createdAt: Date;
  updatedAt: Date;
};

export type CategoryData = {
  id: string;
  title: string;
};

export type ProductData = {
  id: string;
  sellerId: string;
  sku: string;
  title: string;
  imageUrl: string;
  description: string;
  price: number;
  quantity: number;
  category: CategoryData;
  createdAt: Date;
  updatedAt: Date;
};

export type ReviewData = {
  id: string;
  userId: string;
  productId: string;
  rating: number;
  content: string;
  createdAt: Date;
  updatedAt: Date;
};

export type AuthData = {
  userId: string;
  role: Role;
};

export enum OrderStatus {
  PENDING = "PENDING",
  COMPLETED = "COMPLETED",
  CANCELLED = "CANCELLED"
};

export type OrderItemData = {
  id: string;
  productId: string;
  quantity: number;
  subtotal: number;
};

export type OrderData = {
  id: string;
  buyerId: string;
  instruction: string;
  totalPrice: number;
  status: OrderStatus;
  items: OrderItemData[];
  createdAt: Date;
  updatedAt: Date;
};

export type CartData = {
  id: string;
  userId: string;
  items: CartItemData[];
  createdAt: Date;
  updatedAt: Date;
};

export type CartItemData = {
  id: string;
  productId: string;
  quantity: number;
  subtotal: number;
};

export enum DiscountType {
  FIXED = "FIXED",
  PERCENTAGE = "PERCENTAGE",
};

export type DiscountData = {
  id: string;
  description: string;
  type: DiscountType;
  value: number;
  startDate: Date;
  endDate: Date;
  createdAt: Date;
  updatedAt: Date;
};

export type InventoryData = {
  id: string;
  location: string;
  stock: number;
  createdAt: Date;
  updatedAt: Date;
};

// TODO: payment model
export type PaymentData = {
  id: string;
  orderId: string;
  transactionContent: string;
  transactionId: string;
  createdAt: Date;
};
