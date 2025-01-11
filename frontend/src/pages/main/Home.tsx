import CategoryBar from "@/components/category/CategoryBar";
import ProductList from "@/components/product/ProductList";

const Home = () => {
  return (
    <div className="flex flex-1">
      <aside className="w-64 h-full shrink-0 ml-4 my-4 mr-2 sticky top-4">
        <CategoryBar />
      </aside>
      <main className="flex-1 overflow-y-auto mr-4 my-4 ml-2 bg-white rounded-lg">
        <ProductList />
      </main>
    </div>
  );
};

export default Home;