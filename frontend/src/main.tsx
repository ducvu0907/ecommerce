import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { BrowserRouter } from 'react-router-dom'
import "./index.css";
import { Toaster } from "./components/ui/toaster";
import { AuthProvider } from './contexts/AuthContext.tsx'
import { CartProvider } from './contexts/CartContext.tsx'
import { CategoryProvider } from './contexts/CategoryContext.tsx'

const queryClient = new QueryClient();

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <AuthProvider>
          <CartProvider>
            <CategoryProvider>
              <App />
            </CategoryProvider>
          </CartProvider>
        </AuthProvider>
      </QueryClientProvider>
      <Toaster />
    </BrowserRouter>
  </StrictMode>,
)
