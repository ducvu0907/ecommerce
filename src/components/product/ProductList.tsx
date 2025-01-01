import { useContext, useMemo } from "react";
import { getProductsQuery } from "@/services/product";
import ProductItem from "./ProductItem";
import { CategoryContext } from "@/contexts/CategoryContext";
import { Card, CardContent } from "@/components/ui/card";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { Loader2 } from "lucide-react";
import { Badge } from "@/components/ui/badge";
import { filterProductsByCategories } from "@/helpers";

const ProductList = () => {
  const { selectedCategories } = useContext(CategoryContext);
  const { data: products, isLoading, isError, error } = getProductsQuery();

  const filteredProducts = useMemo(() => {
    if (!products?.result) return [];
    return filterProductsByCategories(products.result, selectedCategories || []);
  }, [products, selectedCategories]);

  if (isLoading) {
    return (
      <div className="flex-1 flex justify-center items-center p-8">
        <Loader2 className="h-8 w-8 animate-spin text-primary" />
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex-1 flex justify-center items-center p-8">
        <Alert variant="destructive" className="max-w-md">
          <AlertDescription>
            {error.message || "An error occurred while fetching products"}
          </AlertDescription>
        </Alert>
      </div>
    );
  }

  return (
    <Card className="flex-1 m-4 bg-background">
      <CardContent className="p-6">
        {selectedCategories?.length > 0 && (
          <div className="mb-6">
            <h2 className="text-2xl font-semibold mb-3">Selected Categories</h2>
            <div className="flex flex-wrap gap-2">
              {selectedCategories.map((category) => (
                <Badge key={category.id} variant="secondary">
                  {category.title}
                </Badge>
              ))}
            </div>
          </div>
        )}

        {filteredProducts.length === 0 ? (
          <div className="flex justify-center items-center min-h-[400px]">
            <div className="text-center space-y-4">
              <h3 className="text-xl font-medium text-muted-foreground">
                No products found
              </h3>
              <p className="text-sm text-muted-foreground">
                Try selecting different categories
              </p>
            </div>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {filteredProducts.map((product) => (
              <div key={product.id} className="transition-all hover:scale-[1.02]">
                <ProductItem product={product} />
              </div>
            ))}
          </div>
        )}
      </CardContent>
    </Card>
  );
};

export default ProductList;