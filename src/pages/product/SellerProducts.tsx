import { AuthContext } from "@/contexts/AuthContext";
import { getProductsBySellerQuery } from "@/services/product";
import { useContext } from "react";
import SellerProductItem from "@/components/product/SellerProductItem";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import { useNavigate } from "react-router-dom";

const SellerProducts = () => {
  const { userId } = useContext(AuthContext);
  const { data, isLoading, isError } = getProductsBySellerQuery(userId || "");
  const products = data?.result;
  const navigate = useNavigate();

  if (isLoading) {
    return (
      <div className="space-y-4 max-w-4xl mx-auto">
        {Array.from({ length: 5 }).map((_, index) => (
          <Skeleton key={index} className="h-24 w-full rounded-lg" />
        ))}
      </div>
    );
  }

  if (isError) {
    return (
      <div className="max-w-4xl mx-auto">
        <Alert variant="destructive">
          <AlertDescription>
            Failed to load products. Please try again later.
          </AlertDescription>
        </Alert>
      </div>
    );
  }

  if (!products || products.length === 0) {
    return (
      <div className="max-w-4xl mx-auto space-y-4">
        <div className="text-center">
          <p className="text-gray-500 mb-4">No products found</p>
          <Button onClick={() => navigate("/create-product")} className="gap-2">
            <Plus className="h-4 w-4" />
            New product
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto space-y-6">

      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-semibold">Your Products</h2>
        <Button onClick={() => navigate("/create-product")} className="gap-2">
          <Plus className="h-4 w-4" />
          New product
        </Button>
      </div>

      <div className="space-y-4">
        {products.map((product) => (
          <SellerProductItem key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
};

export default SellerProducts;