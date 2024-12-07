import { getCategories } from "@/services/category";

const CategoryBar = () => {
  const { data: categories, isLoading, isError, error } = getCategories();

  if (isLoading) {
    return (
      <div className="w-full flex justify-center items-center py-4">
        <div className="animate-pulse w-3/4 h-8 bg-gray-200 rounded"></div>
      </div>
    );
  }

  if (isError) {
    return (
      <div className="bg-red-50 border-l-4 border-red-500 p-4 m-4">
        <p className="text-red-700">{error.message}</p>
      </div>
    );
  }

  return (
    <aside className="w-64 bg-white shadow-md rounded-lg p-4 space-y-2">
      <h2 className="text-xl font-semibold text-gray-800 mb-4 border-b pb-2">Categories</h2>
      <nav>
        <ul className="space-y-1">
          {categories?.result?.map((category) => (
            <li 
              key={category.id} 
              className="group cursor-pointer"
            >
              <div className="px-3 py-2 rounded-md text-gray-700 hover:bg-gray-100 hover:text-gray-900 transition-colors duration-200">
                <span className="font-medium group-hover:text-gray-900">
                  {category.title}
                </span>
              </div>
            </li>
          ))}
        </ul>
      </nav>
    </aside>
  );
};

export default CategoryBar;