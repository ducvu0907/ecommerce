import { useLocation } from "react-router-dom";
import ProductItem from "@/components/product/ProductItem";
import { searchProductsQuery } from "@/services/product";
import { Card, CardContent } from "@/components/ui/card";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { Loader2 } from "lucide-react";

const SearchProducts = () => {
  const location = useLocation();
  const query = location.state as string;
  const { data: products, isLoading, isError, error } = searchProductsQuery(query);

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
        {query && (
          <h2 className="text-2xl font-semibold mb-6">
            Search results for "{query}"
          </h2>
        )}
        
        {products?.result?.length === 0 ? (
          <div className="flex justify-center items-center min-h-[400px]">
            <div className="text-center space-y-4">
              <h3 className="text-xl font-medium text-muted-foreground">
                No products found
              </h3>
              <p className="text-sm text-muted-foreground">
                Try adjusting your search terms
              </p>
            </div>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6">
            {products?.result?.map((product) => (
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

export default SearchProducts;