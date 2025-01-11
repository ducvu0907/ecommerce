import { authenticateQuery } from '@/services/auth';
import { Role } from '@/types/models';
import React, { createContext, useState, ReactNode, useEffect } from 'react';

interface AuthContextType {
  token: string | null;
  setToken: (token: string | null) => void;
  role: Role | null;
  setRole: (role: Role | null) => void;
  userId: string | null;
  setUserId: (userId: string | null) => void;
}

export const AuthContext = createContext<AuthContextType>({
  token: null,
  setToken: () => {},
  role: null,
  setRole: () => {},
  userId: null,
  setUserId: () => {},
});

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [token, setToken] = useState<string | null>(null);
  const [role, setRole] = useState<Role | null>(null);
  const [userId, setUserId] = useState<string | null>(null);
  const { data: authData } = authenticateQuery();

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);

  useEffect(() => {
    if (authData?.result) {
      setUserId(authData.result?.userId);
      setRole(authData.result?.role);
    }
  }, [token, authData]);

  return (
    <AuthContext.Provider value={{ token, setToken, role, setRole, userId, setUserId }}>
      {children}
    </AuthContext.Provider>
  );
};
