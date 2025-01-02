import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Label } from "@/components/ui/label";
import { getCategoriesQuery } from "@/services/category";
import { createProductMutation } from "@/services/product";
import { CreateProductRequest } from "@/types/models";
import { CategoryData } from "@/types/models";
import { useNavigate } from "react-router-dom";

const CreateProduct = () => {
  const { mutate: createProduct, isPending } = createProductMutation();
  const { data, isLoading } = getCategoriesQuery();
  const categories = data?.result;
  const navigate = useNavigate();

  const [formData, setFormData] = useState<CreateProductRequest>({
    title: "",
    description: "",
    imageUrl: "",
    sku: "",
    price: 0,
    categoryId: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    createProduct({ request: formData });
    navigate("/my-products");
  };

  if (isLoading) {
    return <div>Loading categories...</div>;
  }

  return (
    <div className="max-w-md mx-auto p-6 bg-white shadow-md rounded-lg">
      <h1 className="text-2xl font-bold mb-6">Create Product</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <Label htmlFor="title">Title</Label>
          <Input
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            placeholder="Product Title"
            required
          />
        </div>
        <div>
          <Label htmlFor="description">Description</Label>
          <Input
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            placeholder="Product Description"
            required
          />
        </div>
        <div>
          <Label htmlFor="imageUrl">Image URL</Label>
          <Input
            id="imageUrl"
            name="imageUrl"
            value={formData.imageUrl}
            onChange={handleChange}
            placeholder="Image URL"
            required
          />
        </div>
        <div>
          <Label htmlFor="sku">SKU</Label>
          <Input
            id="sku"
            name="sku"
            value={formData.sku}
            onChange={handleChange}
            placeholder="Product SKU"
            required
          />
        </div>
        <div>
          <Label htmlFor="price">Price</Label>
          <Input
            id="price"
            name="price"
            type="number"
            value={formData.price}
            onChange={handleChange}
            placeholder="Product Price"
            required
          />
        </div>
        <div>
          <Label htmlFor="categoryId">Category</Label>
          <Select
            name="categoryId"
            value={formData.categoryId}
            onValueChange={(value) => setFormData((prev) => ({ ...prev, categoryId: value }))}
            required
          >
            <SelectTrigger>
              <SelectValue placeholder="Select a category" />
            </SelectTrigger>
            <SelectContent>
              {categories?.map((category: CategoryData) => (
                <SelectItem key={category.id} value={category.id}>
                  {category.title}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <Button type="submit" disabled={isPending} className="w-full">
          {isPending ? "Creating..." : "Create Product"}
        </Button>
      </form>
    </div>
  );
};

export default CreateProduct;