package br.com.casa.programador.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.casa.programador.models.Blocks;
import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.models.TrocaSenha;
import br.com.casa.programador.models.users.Inscrito;
import br.com.casa.programador.models.users.Publicador;
import br.com.casa.programador.repository.PublicacaoRepository;
import br.com.casa.programador.repository.PublicadorRepository;

@Controller
@RequestMapping("/publicador")
public class PublicadorController {
	
	public static String uploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imageUpload";

	private Publicacao pub;

	@Autowired
	Validador validador;

	@Autowired
	PublicadorRepository pRepository;

	@Autowired
	PublicacaoRepository pubRepository;


	@RequestMapping(value = "/acessoPublicador", method = RequestMethod.GET)
	public String acessoPublicador(Publicador publicador, Model model, Principal principal) {
		publicador = pRepository.findByEmail(principal.getName());
		model.addAttribute("publicador", publicador);
		return "publicador/telaPublicador";
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.GET)
	public String alterarSenha(TrocaSenha trocaSenha, Model model) {
		return "publicador/alterarSenha";
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
	public String alterarSenha(@ModelAttribute @Valid TrocaSenha trocaSenha, BindingResult result, Model model,
			Errors errors, Principal principal, RedirectAttributes mensagem) {
		if (!validador.alterarSenha(trocaSenha, result, model, errors, principal.getName())) {
			return alterarSenha(trocaSenha, model);
		}
		mensagem.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
		return "redirect:/publicador/alterarSenha";
	}

	@RequestMapping(value = "/publicadorEditar", method = RequestMethod.GET)
	public String publicadorEditar(@ModelAttribute("publicador") Publicador publicador, Model model, Principal principal) {
		publicador = pRepository.findByEmail(principal.getName());
		model.addAttribute("publicador", publicador);
		return "publicador/publicadorEditar";
	}

	
	@RequestMapping(value = "/publicadorEditar", method = RequestMethod.POST)
	public String inscritoEditar(@ModelAttribute("inscrito") Publicador publicador, BindingResult result, Model model,
			RedirectAttributes mensagem, Principal principal) {
		if (result.hasErrors()) {
			return publicadorEditar(publicador, model, principal);
		}
		publicador = publicadorTranspassado(publicador);
		pRepository.save(publicador);
		mensagem.addFlashAttribute("mensagem", "Alterações feitas com sucesso");
		return "redirect:/publicador/publicadorEditar";
	}

	public Publicador publicadorTranspassado(Publicador pub) {
		Optional<Publicador> publicadorAntigo = pRepository.findById(pub.getId());
		Publicador publicador;
		if (publicadorAntigo.isPresent()) {
			publicador = publicadorAntigo.get();
			publicador.setDatanasc(pub.getDatanasc());
			publicador.setNome(pub.getNome());
			return publicador;
		} else {
			return null;
		}
	}
	
	@ModelAttribute("publicador")
	public Publicador novoPublicador() {
		return new Publicador();
	}
	
	//A partir daqui são os recursos da edição/publicação gerada
	
	@PostMapping("/salvarEdicao")
	public String criarJson(@RequestBody String json) {
		Gson gson = new Gson();
		Publicacao pub = gson.fromJson(json, Publicacao.class);
		for (Blocks b : pub.getBlocks()) {
			b.getData().setBlock(b);
			b.setPub(pub);
		}
		this.pub = pub;
		return "redirect:/editor/editar";
	}

	@RequestMapping(value = "/salvarPublicacao", method = RequestMethod.POST)
	public String salvarPublicacao(@RequestParam("imagemGrande") MultipartFile imagemGrande, 
			@RequestParam("imagemPequena") MultipartFile imagemPequena,
			@RequestParam("nomeArtigo") String nomeArtigo,
			RedirectAttributes mensagem) {
		System.out.println("passou");
		String imagemGrandeS = salvarArquivo(imagemGrande);
		String imagemPequenaS = salvarArquivo(imagemPequena);
		if (this.pub == null) {
			mensagem.addFlashAttribute("mensagemFail", "publicacao não pode ser salva =(, por favor contate um administrador caso persista");
		}else{
			this.pub.setImageGrandeUrl(uploadDirectory + "\\" + imagemGrandeS);
			this.pub.setImagemPequenaUrl(uploadDirectory + "\\" + imagemPequenaS);
			this.pub.setNome(nomeArtigo);
			pubRepository.save(this.pub);
			this.pub = null;
			mensagem.addFlashAttribute("mensagem", "publicacao salva com sucesso!");
		}
		return "redirect:/editor/editar";
	}

	@RequestMapping(value = "/salvarPublicacao", method = RequestMethod.GET)
	public String salvarPublicacao() {
		return "paginaEdicao/salvarEdicao";
	}

	private String salvarArquivo(MultipartFile file) {
		StringBuilder fileName = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getOriginalFilename();
	}

}
