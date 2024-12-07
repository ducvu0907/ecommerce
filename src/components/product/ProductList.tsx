import { getProducts } from "@/services/product";
import ProductItem from "./ProductItem";

const ProductList = () => {
  const {data: products, isLoading, isError, error} = getProducts(); 

  if (isLoading) {
    return (
      <div className="w-full flex justify-center items-center">
        <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-orange-500"></div>
      </div>
    );
  }

  if (isError) {
    return (
      <div className="w-full h-screen flex items-center justify-center">
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
          <span className="block sm:inline">{error.message}</span>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-6">

      <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-5 gap-4">
        {products?.result?.map((product) => (
          <ProductItem 
            key={product.id} 
            product={product} 
          />
        ))}
      </div>
      
      {products?.result?.length === 0 && (
        <div className="text-center py-10 text-gray-500">
          No products found.
        </div>
      )}
    </div>
  );
};

export default ProductList;