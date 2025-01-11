import { CategoryData } from "@/types/models";
import { createContext, ReactNode, useState } from "react"

interface CategoryContextType {
  selectedCategories: CategoryData[];
  setSelectedCategories: (categories: CategoryData[]) => void;
};

export const CategoryContext = createContext<CategoryContextType>({
  selectedCategories: [],
  setSelectedCategories: () => {},
});

interface CategoryProviderProps {
  children: ReactNode;
};

export const CategoryProvider: React.FC<CategoryProviderProps> = ({ children }) => {
  const [selectedCategories, setSelectedCategories] = useState<CategoryData[]>([]);

  return (
    <CategoryContext.Provider value={{ selectedCategories, setSelectedCategories }}>
      {children}
    </CategoryContext.Provider>
  );
};